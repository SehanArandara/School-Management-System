package org.schoolmanagement.studentmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(value="marks")
public class Mark {
    @Id
    private String id;
    private String studentId;
    private String examId;
    private String subjectId;
    private double marksObtained;
    private double totalMarks;
    private String grade;
    private String dateOfExam;
    private String comments;
    private String teacherId;
    private String classId;
    private String schoolId;
}
