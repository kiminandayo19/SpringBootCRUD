package com.arif.bootcamp.entity;

import com.arif.bootcamp.dto.AuditingDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class BaseFile extends AuditingDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fileName;
    private String fileType;
    @Column(columnDefinition = "TEXT")
    private String fileData;
}