package com.simpleSpringProject.SchoolManagementSystem.controller;

import com.simpleSpringProject.SchoolManagementSystem.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/StudentController/api")
public class StudentController {

    // here we are using Hashmap for DataBase operations (CRUD)
    HashMap<Integer, Student> studentDB=new HashMap<>();

//    1.(1,"abcd","01/06/1999","male","B");
//    2.(1,"pqrs","03/06/2000","female","A");
//    3.(1,"xyz","01/08/1999","male","C");


    // @RequestBody -> takes input and request from UI/postman and assign it to object
    @PostMapping("/save")
    public String saveStudent( @RequestBody Student studentRequest){
        // take student request and add it inside the HashMap
        studentDB.put(studentRequest.getStudentID(), studentRequest);
        return "Student added successfully.";
    }

    @GetMapping("/findAll")
    public  HashMap<Integer, Student> getAllStudent(){
        return studentDB;

    }


    // @PathVariable -> input is sent in url path(endpoint)

    @GetMapping("/find/{studentid}")
    public Student getStudentByID(@PathVariable("studentid") int studentID){
        Student student= studentDB.get(studentID);
        return student;
    }

    // @RequestParam -> it takes the input in the form of a request parameter as a query

    @GetMapping("/findByName")
          public Student getStudentByName(@RequestParam("name") String name) {
        for (Student student : studentDB.values()) {
            if (student.getStudentName().equals(name)) {
                return student;
            }
        }
       return null;

   }

        @GetMapping("/findByGrade")
        public List<Student> getStudentByGrade(@RequestParam("grade") String grade){
            List<Student> studentList=new ArrayList<>();
            for(Student student: studentDB.values()){
                if(student.getGrade().equals(grade)){
                    studentList.add(student) ;
                }
            }
            return studentList;
        }



    @GetMapping("/findByNameAndGrade")
    public List<Student> getStudentByNameAndGrade(@RequestParam("name") String name,@RequestParam("grade") String grade){
        List<Student> studentList=new ArrayList<>();
        for(Student student: studentDB.values()){
            if(student.getStudentName().equals(name) && student.getGrade().equals(grade)){
                studentList.add(student) ;
            }
        }
        return studentList;
    }
        @DeleteMapping("/delete/{studentid}")
        public String deleteStudentById(@PathVariable("studentid") int studentID){
            studentDB.remove(studentID);
            return "Student with id "+studentID+" got deleted";
        }

         @PutMapping("/update/{studentid}")
        public String updateStudent(@PathVariable("studentid") int studentID, @RequestBody Student studentRequest){
              // find student details with id
             // if student is present update the student detail
            //else not update
             Student student= studentDB.get(studentID);
             if(student !=null){
                 studentDB.put(studentID, studentRequest);
                 return " Student detail is updated";
             }
             else{
                 return "Student not found we can not update student details";
             }

        }


    }

