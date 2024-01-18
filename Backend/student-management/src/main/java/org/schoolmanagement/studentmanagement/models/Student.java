package org.schoolmanagement.studentmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "Students")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data

public class Student {
    @Id
    private String id;
    private String firstName ;
    private String lastName ;
    private String birthDay;
    private String address;
    private String grade;
    private String contactNumber ;

}
