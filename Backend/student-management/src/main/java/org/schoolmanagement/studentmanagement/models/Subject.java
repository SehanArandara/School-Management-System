package org.schoolmanagement.studentmanagement.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(value = "subject")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Subject {
    @Id
    private String id;
    private String name;
    private String category;
}
