package com.packs.counproc.services;

import com.packs.counproc.models.CollegeModels.*;
import com.packs.counproc.models.RegisterModel.RegisterStudent;
import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.models.requests.AddDepartment;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.*;
import com.packs.counproc.repositories.college.*;
import com.packs.counproc.repositories.register.RegisterStudentRepo;
import com.packs.counproc.repositories.register.StudentCollegeRepo;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegeServices {

    @Autowired
    CollegesRepo collegesRepo;

    @Autowired
    DepartmentListRepo departmentListRepo;

    @Autowired
    DatabaseSequenceRepo databaseSequenceRepo;

    @Autowired
    ClassroomRepo classroomRepo;

    @Autowired
    StudentCollegeRepo studentCollegeRepo;

    @Autowired
    StudentClassRepo studentClassRepo;

    @Autowired
    ClassStatsRepo classStatsRepo;

    @Autowired
    RegisterStudentRepo registerStudentRepo;

    @Autowired
    Utils utils;

    public ApiResponse getAllColleges() {
        return new ApiResponse(200, HttpStatus.OK, collegesRepo.findAll(), "All Colleges");
    }

    public ApiResponse addCollege(Colleges college) {
        college.setCollegeId(utils.getIncCount("Colleges"));
        Colleges collegeObj = collegesRepo.save(college);
        return new ApiResponse(200, HttpStatus.OK, collegeObj.getCollegeId(), "College Added");
    }

    public ApiResponse getAllDepartment() {
        return new ApiResponse(200, HttpStatus.OK, departmentListRepo.findAll(), "All Departments");
    }

    public ApiResponse addDepartment(AddDepartment addDepartment) {
        List<Colleges> colleges = collegesRepo.findByCollegeName(addDepartment.getCollegeName());
        if (!colleges.isEmpty()) {
            DepartmentList department = new DepartmentList();
            department.setCollegeId(colleges.get(0).getCollegeId());
            department.setDeptName(addDepartment.getDepartmentName());
            department.setDeptDesc(addDepartment.getDescription());
            department.setInTakeCount(addDepartment.getInTakeCount());
            department.setDeptId(utils.getIncCount("DepartmentList"));
            departmentListRepo.save(department);
            return new ApiResponse(200, HttpStatus.OK, "Department Added");
        } else {
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid college");
        }

    }

    public ApiResponse addClassroom(int collegeId, Classrooms addClassroom) {
        List<DepartmentList> deptList = departmentListRepo.findByDeptName(addClassroom.getDeptName());
        if (!deptList.isEmpty()) {
            for (DepartmentList department : deptList) {
                if (department.getCollegeId() == collegeId) {

                    addClassroom.setCollegeId(collegeId);
                    addClassroom.setDeptId(deptList.get(0).getDeptId());
                    addClassroom.setSectionId(utils.getIncCount("ClassRoomId"));
                    classroomRepo.save(addClassroom);

                    ClassStats classStats = new ClassStats();
                    classStats.setSectionId(addClassroom.getSectionId());
                    classStats.setGroupOneCount(0);
                    classStats.setGroupTwoCount(0);
                    classStats.setGroupThreeCount(0);
                    classStatsRepo.save(classStats);
                    return new ApiResponse(200, HttpStatus.OK, "Classrooms Added");
                }
            }
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid College ID");
        } else
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid Department Name");
    }

    public ApiResponse getAllClassrooms(int collegeId) {
        return new ApiResponse(200, HttpStatus.OK,
                classroomRepo.findByCollegeId(collegeId), "All Classrooms");
    }


    public ApiResponse addClassStats(ClassStats classStats) {
        classStatsRepo.save(classStats);
        return new ApiResponse(200, HttpStatus.OK, "ClassStats Added");
    }

    public ApiResponse getClassStats() {
        return new ApiResponse(200, HttpStatus.OK, classStatsRepo.findAll(), "All ClassStats ");
    }

    public ApiResponse getStudentClassMap() {
        return new ApiResponse(200, HttpStatus.OK, studentClassRepo.findAll(), "StudentClassMap");
    }

    public ApiResponse assignclasses(int collegeId) {
        boolean flag = false;
        // get all classrooms in the college
        List<Classrooms> classroomsInCollege = classroomRepo.findByCollegeId(collegeId);
        // get all student registered to the particular college
        List<StudentCollegeMap> studentsInCollege = studentCollegeRepo.findByCollegeId(collegeId);
        for (StudentCollegeMap student : studentsInCollege) {
            // find the group to which the student belong to
            int studentGroup = getStudentGroup(student);
            List<Classrooms> matchedClassroomList = classroomsInCollege.stream()
                    .filter(classroom -> classroom.getDeptId() == student.getDepartmentId()
                    ).collect(Collectors.toList());
            // find which section the student belong and get section id
            int sectionId = getSectionId(matchedClassroomList, studentGroup);
            updateDatabase(sectionId, student);
        }
        if (flag)
            return new ApiResponse(200, HttpStatus.OK, "Classrooms Assigned");
        else
            return new ApiResponse(201, HttpStatus.SERVICE_UNAVAILABLE, "Classrooms NOT Assigned or already assigned");
    }

    public int getSectionId(List<Classrooms> matchedClassList, int studentGroup) {
        int sectionId = 0;
        for (Classrooms classroom : matchedClassList) {
            ClassStats stats = classStatsRepo.findAllBySectionId(classroom.getSectionId());
            switch (studentGroup) {
                case 1: {
                    if (stats.getGroupOneCount() > 0) {
                        stats.setGroupOneCount(stats.getGroupOneCount() - 1);
                        sectionId = stats.getSectionId();
                        break;
                    }
                }
                case 2: {
                    if (stats.getGroupTwoCount() > 0) {
                        stats.setGroupTwoCount(stats.getGroupTwoCount() - 1);
                        sectionId = stats.getSectionId();
                        break;
                    }
                }
                case 3: {
                    if (stats.getGroupThreeCount() > 0) {
                        stats.setGroupThreeCount(stats.getGroupThreeCount() - 1);
                        sectionId = stats.getSectionId();
                        break;
                    }
                }
            }
            if (sectionId != 0)
                break;
        }
        return sectionId;
    }

    public int getStudentGroup(StudentCollegeMap student) {
        RegisterStudent studentProfile = registerStudentRepo.findAllByRegId(student.getStudentId());
        int markPercent = studentProfile.getMarksPercentage();
        if (markPercent > 70)
            return 1;
        else if (markPercent > 40 && markPercent < 70)
            return 2;
        else
            return 3;
    }

    public boolean updateDatabase(int sectionId, StudentCollegeMap student) {
        try {
            // update classroom (desc totalstudent count ) and student-class map
            Classrooms studentClassroom = classroomRepo.findBySectionId(sectionId);
            studentClassroom.setTotalStudents(studentClassroom.getTotalStudents() - 1);
            classroomRepo.save(studentClassroom);
            StudentClassMap studentClassMap = new StudentClassMap();
            studentClassMap.setStudentId(student.getStudentId());
            studentClassMap.setCollegId(student.getCollegeId());
            studentClassMap.setSectionId(studentClassroom.getSectionId());
            studentClassMap.setSectionName(studentClassroom.getSectionName());
            studentClassMap.setDeptId(student.getDepartmentId());
            studentClassMap.setDeptName(studentClassroom.getDeptName());
            studentClassRepo.save(studentClassMap);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
