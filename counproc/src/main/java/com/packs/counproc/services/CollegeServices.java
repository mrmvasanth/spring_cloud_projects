package com.packs.counproc.services;

import com.packs.counproc.MongoServer.models.CollegeModels.*;
import com.packs.counproc.MongoServer.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.MongoServer.models.requests.AddDepartment;
import com.packs.counproc.MongoServer.repositories.*;
import com.packs.counproc.MongoServer.repositories.college.*;
import com.packs.counproc.MongoServer.repositories.register.StudentCollegeRepo;
import com.packs.counproc.MysqlServer.models.RegisterStudent;
import com.packs.counproc.MysqlServer.repositories.StudentRegRepository;
import com.packs.counproc.models.ApiResponseBody;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
    StudentRegRepository studentRegRepository;

    @Autowired
    Utils utils;

    public ResponseEntity<ApiResponseBody> getAllColleges() {
        ApiResponseBody apiResponseBody = new ApiResponseBody(collegesRepo.findAll(), "All colleges");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> addCollege(Colleges college) {
        ApiResponseBody apiResponseBody = null;
        try {
            List<Colleges> colleges = collegesRepo.findByCollegeName(college.getCollegeName());
            if (colleges.isEmpty()) {
                college.setCollegeId(utils.getIncCount("Colleges"));
                collegesRepo.save(college);
                apiResponseBody = new ApiResponseBody("Colleges added successfully");
            } else
                apiResponseBody = new ApiResponseBody(409, HttpStatus.CONFLICT, "Colleges already present");
            return ResponseEntity.ok(apiResponseBody);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Internal server error");
        }

    }

    public ResponseEntity<ApiResponseBody> getAllDepartment() {
        ApiResponseBody apiResponseBody = new ApiResponseBody(departmentListRepo.findAll(), "All Departments");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> addDepartment(AddDepartment addDepartment) {
        ApiResponseBody apiResponseBody = null;
        List<Colleges> colleges = collegesRepo.findByCollegeName(addDepartment.getCollegeName());
        if (!colleges.isEmpty()) {
            if (!departmentListRepo.existsByCollegeIdAndDeptName(colleges.get(0).getCollegeId(),
                    addDepartment.getDepartmentName())) {


                DepartmentList department = new DepartmentList();
                department.setCollegeId(colleges.get(0).getCollegeId());
                department.setDeptName(addDepartment.getDepartmentName());
                department.setDeptDesc(addDepartment.getDescription());
                department.setInTakeCount(addDepartment.getInTakeCount());
                department.setDeptId(utils.getIncCount("DepartmentList"));
                departmentListRepo.save(department);
                apiResponseBody = new ApiResponseBody("Department added");
            } else
                apiResponseBody = new ApiResponseBody("Already present");
        } else {
            apiResponseBody = new ApiResponseBody(400, HttpStatus.BAD_REQUEST, "Invalid college name");
        }
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> addClassroom(int collegeId, Classrooms addClassroom) {
        boolean flag = false;
        ApiResponseBody apiResponseBody = null;
        List<DepartmentList> deptList = departmentListRepo.findByDeptName(addClassroom.getDeptName());
        if (!deptList.isEmpty()) {
            for (DepartmentList department : deptList) {
                if (department.getCollegeId() == collegeId) {
                    flag = classroomRepo.existsByCollegeIdAndDeptIdAndSectionName(department.getCollegeId(),
                            department.getDeptId(), addClassroom.getSectionName());
                    if (!flag) {
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
                        apiResponseBody = new ApiResponseBody("Classrooms Added");
                        return ResponseEntity.ok(apiResponseBody);
                    } else {
                        apiResponseBody = new ApiResponseBody(409, HttpStatus.CONFLICT,
                                "Classroom already present");
                        return ResponseEntity.ok(apiResponseBody);
                    }

                }
            }
            apiResponseBody = new ApiResponseBody(404, HttpStatus.NOT_FOUND, "Invalid College ID");
        } else
            apiResponseBody = new ApiResponseBody(404, HttpStatus.NOT_FOUND, "Invalid Department Name");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> getAllClassrooms(int collegeId) {
        ApiResponseBody apiResponseBody = new ApiResponseBody(classroomRepo.findByCollegeId(collegeId), "All classrooms");
        return ResponseEntity.ok(apiResponseBody);

    }

    public ResponseEntity<ApiResponseBody> deleteClassroom(String classroomId) {
        classroomRepo.deleteById(classroomId);
        ApiResponseBody apiResponseBody = new ApiResponseBody("Classrooms deleted");
        return ResponseEntity.ok(apiResponseBody);
    }


    public ResponseEntity<ApiResponseBody> addClassStats(ClassStats classStats) {
        Optional<ClassStats> classStatsFromDB=classStatsRepo.findBySectionId(classStats.getSectionId());
        if(classStatsFromDB.isPresent()){
            classStats.setId(classStatsFromDB.get().getId());
            classStatsRepo.save(classStats);
            ApiResponseBody apiResponseBody = new ApiResponseBody("ClassStats Updated");
            return ResponseEntity.ok(apiResponseBody);
        }else {
            classStatsRepo.save(classStats);
            ApiResponseBody apiResponseBody = new ApiResponseBody("ClassStats Added");
            return ResponseEntity.ok(apiResponseBody);
        }

    }

    public ResponseEntity<ApiResponseBody> getClassStats() {
        ApiResponseBody apiResponseBody = new ApiResponseBody(classStatsRepo.findAll(), "All ClassStats ");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> deleteStats() {
        classStatsRepo.deleteAll();
        ApiResponseBody apiResponseBody = new ApiResponseBody("All ClassStats Deleted");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> getStudentClassMap() {
        ApiResponseBody apiResponseBody = new ApiResponseBody(studentClassRepo.findAll(), "StudentClassMap");
        return ResponseEntity.ok(apiResponseBody);
    }

    public ResponseEntity<ApiResponseBody> assignclasses(int collegeId) {
        ApiResponseBody apiResponseBody = null;
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
            flag = updateDatabase(sectionId, student);
        }
        if (flag)
            apiResponseBody = new ApiResponseBody(200, HttpStatus.OK, "Classrooms Assigned");
        else
            apiResponseBody = new ApiResponseBody(201, HttpStatus.SERVICE_UNAVAILABLE, "Classrooms NOT Assigned or already assigned");

        return ResponseEntity.ok(apiResponseBody);
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
                        classStatsRepo.save(stats);
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
//        RegisterStudent studentProfile = registerStudentRepo.findAllByRegId(student.getStudentId());
        Optional<RegisterStudent> studentProfile = studentRegRepository.findById(student.getStudentId());
        int markPercent = studentProfile.get().getMarksPercentage();
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
