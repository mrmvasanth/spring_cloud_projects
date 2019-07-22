package com.packs.counproc.services;

import com.packs.counproc.models.CollegeModels.Classrooms;
import com.packs.counproc.models.CollegeModels.Colleges;
import com.packs.counproc.models.CollegeModels.DepartmentList;
import com.packs.counproc.models.CollegeModels.StudentClassMap;
import com.packs.counproc.models.RegisterModel.StudentCollegeMap;
import com.packs.counproc.models.requests.AddDepartment;
import com.packs.counproc.models.responses.ApiResponse;
import com.packs.counproc.repositories.*;
import com.packs.counproc.repositories.college.ClassroomRepo;
import com.packs.counproc.repositories.college.CollegesRepo;
import com.packs.counproc.repositories.college.DepartmentListRepo;
import com.packs.counproc.repositories.college.StudentClassRepo;
import com.packs.counproc.repositories.register.StudentCollegeRepo;
import com.packs.counproc.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

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
    Utils utils;

    public ApiResponse getAllColleges(){
        return new ApiResponse(200, HttpStatus.OK, collegesRepo.findAll(), "All Colleges");
    }

    public ApiResponse addCollege(Colleges college) {
        college.setCollegeId(utils.getIncCount("Colleges"));
        Colleges collegeObj = collegesRepo.save(college);
        return new ApiResponse(200, HttpStatus.OK, collegeObj.getCollegeId(), "College Added");
    }

    public ApiResponse getAllDepartment(){
        return new ApiResponse(200,HttpStatus.OK,departmentListRepo.findAll(),"All Departments");
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
        }else{
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid college");
        }

    }

    public ApiResponse addClassroom(int collegeId, Classrooms addClassroom){
        List<DepartmentList> deptList=departmentListRepo.findByDeptName(addClassroom.getDeptName());
        if (!deptList.isEmpty()){
            for (DepartmentList department:deptList) {
                if(department.getCollegeId()==collegeId){
                    addClassroom.setCollegeId(collegeId);
                    addClassroom.setDeptId(deptList.get(0).getDeptId());
                    addClassroom.setSectionId(utils.getIncCount("ClassRoomId"));
                    classroomRepo.save(addClassroom);
                    return new ApiResponse(200, HttpStatus.OK, "Classrooms Added");
                }
            }
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid College ID");
        }else
            return new ApiResponse(404, HttpStatus.NOT_FOUND, "Invalid Department Name");
    }

    public ApiResponse getAllClassrooms(int collegeId){
        return new ApiResponse(200, HttpStatus.OK,
                classroomRepo.findByCollegeId(collegeId),"All Classrooms");
    }

    public ApiResponse assignclasses(int collegeId){
        // get students by college id
        List<StudentCollegeMap> studentsList=studentCollegeRepo.findByCollegeId(collegeId);
        for (StudentCollegeMap student:studentsList) {
            List<Classrooms> classrooms=classroomRepo.findByCollegeIdAndDeptId(collegeId,student.getDepartmentId());
            for(Classrooms classroom:classrooms){
                if(classroom.getTotalStudents()>0){
                    assignClassroom(classroom,student);
                }
            }
        }
        return new ApiResponse(200, HttpStatus.OK, "Classrooms Assigned");
    }

    public void assignClassroom(Classrooms classroom, StudentCollegeMap student){

        // update total student count in a classroomd
        Classrooms classrooms = classroomRepo.findBySectionId(classroom.getSectionId());
        classroom.setTotalStudents(classrooms.getTotalStudents()-1);
        classroomRepo.save(classroom);

        // store student-class mapping details
        StudentClassMap studentClassMap=new StudentClassMap();
        studentClassMap.setCollegId(student.getCollegeId());
        studentClassMap.setDeptId(classroom.getDeptId());
        studentClassMap.setDeptName(classroom.getDeptName());
        studentClassMap.setSectionId(classroom.getSectionId());
        studentClassMap.setSectionName(classroom.getSectionName());
        studentClassMap.setStudentId(student.getStudentId());
        studentClassRepo.save(studentClassMap);
    }

    public ApiResponse getStudentClassMap(){
        return new ApiResponse(200,HttpStatus.OK, studentClassRepo.findAll(),"StudentClassMap");
    }

}
