package org.schoolmanagement.studentmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(value = "exam")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Exam {
    @Id
    private String id;
    private String examName;
    private String stdId;
    private String subjectId;
    private String date;
    private String time;
    private String venue;
}
