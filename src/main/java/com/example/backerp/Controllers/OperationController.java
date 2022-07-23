package com.example.backerp.Controllers;


import com.example.backerp.Repositories.AssociationsRepository;
import com.example.backerp.Repositories.ConventionsRepository;
import com.example.backerp.Repositories.EvenementRepository;
import com.example.backerp.Tools.Broadcaster;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@RestController
@RequestMapping("/operation")
public class OperationController {


    @Autowired
    private Broadcaster BR;

    @GetMapping("/excel")
    public void excelReader(@RequestParam("file") MultipartFile excel) {
        BR = new Broadcaster();

        if (BR.LoadMainsheet(excel)) {
            System.out.println("Excel File Loaded");

        }


    }
}