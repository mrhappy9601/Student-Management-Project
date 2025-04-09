package com.example.student_management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StudentDTO {

   @NotBlank(message ="Name is required")
   private String name;
   
   @NotNull(message="Age is Required")
   @Min(value=18, message="Age should be at least 18")
   private int age;
   
   @NotBlank(message="Email is required")
   @Email(message="Invalid email format")
   private String email;

   // Getters and setters
   public String getName() {
       return name;
   }

   public void setName(String name) {
       this.name = name;
   }

   public int getAge() {
       return age;
   }

   public void setAge(int age) {
       this.age = age;
   }

   public String getEmail() {
       return email;
   }

   public void setEmail(String email) {
       this.email = email;
   }
}
