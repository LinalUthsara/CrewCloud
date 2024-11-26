package com.darkgenesis.crewcloud.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class RegularEmployee extends Employee{
    
}
