package com.equipment.school_equipment.service;


import com.equipment.school_equipment.repository.ClassTimeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClassTimeService {
    private final ClassTimeRepository classTimeRepository;

}
