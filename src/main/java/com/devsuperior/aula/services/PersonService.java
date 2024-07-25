package com.devsuperior.aula.services;

import com.devsuperior.aula.dto.PersonDepartmenDTO;
import com.devsuperior.aula.entities.Department;
import com.devsuperior.aula.entities.Person;
import com.devsuperior.aula.repositories.DepartmentRepository;
import com.devsuperior.aula.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    public PersonDepartmenDTO insert(PersonDepartmenDTO dto){
        Person person = new Person();
        person.setName(dto.getName());
        person.setSalary(dto.getSalary());

        //Desta forma retorna o objeto assim
        //
        //Department department = new Department();
        //department.setId(dto.getDepartment().getId());
//        {
//            "id": 5,
//                "name": "Nova Pessoa",
//                "salary": 1.2345678901E10,
//                "department": {
//            "id": 1,
//                    "name": null
//        }
//        }
       Department department = departmentRepository.getReferenceById(dto.getDepartment().getId());
       person.setDepartment(department);
       person = personRepository.save(person);

        return new PersonDepartmenDTO(person);
    }
}
