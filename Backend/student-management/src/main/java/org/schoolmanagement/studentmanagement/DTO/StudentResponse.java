package org.schoolmanagement.studentmanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResponse {
    private String id;
    private String firstName ;
    private String lastName ;
    private String birthDay;
    private String address;
    private String grade;
    private String contactNumber ;
}
