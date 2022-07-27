package com.example.backerp.Controllers;


import com.example.backerp.Models.Associations;
import com.example.backerp.Models.Conventions;
import com.example.backerp.Models.Detailevenement;
import com.example.backerp.Models.Evenement;
import com.example.backerp.Repositories.AssociationsRepository;
import com.example.backerp.Repositories.ConventionsRepository;
import com.example.backerp.Repositories.DetailevenementRepository;
import com.example.backerp.Repositories.EvenementRepository;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;

@RestController
@RequestMapping("/operation")
public class OperationController {


    @Autowired
    private AssociationsRepository AR;
    @Autowired
    private ConventionsRepository CR;
    @Autowired
    private DetailevenementRepository ER;
    @Autowired
    private EvenementRepository EE;

    public boolean AddConvention(Conventions C) {
        System.out.println("AddConvention");
        try {
            CR.save(C);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean AddAssociation(Associations A) {
        System.out.println("Adding Association");
        // display A in console
        System.out.println("Libelle" + A.getLibelle() + " Reference" + A.getReference());
        boolean Res = false;

        try {
            this.AR.save(A);
            System.out.println("Association Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println(E.getMessage());
        }
        return Res;
    }

    public boolean AddDetailEvent(Detailevenement Detail) {
        boolean Res = false;
        try {
            ER.save(Detail);
            System.out.println("Detail Evenement Saved");
            Res = true;
        } catch (Exception E) {
            System.out.println("Detail Evenement non trouvée" + E.getMessage());
        }
        return Res;
    }

    public boolean AddEvent(Evenement E) {
        boolean Res = false;
        try {
            EE.save(E);
            System.out.println("Evenement Saved");
            Res = true;
        } catch (Exception E1) {
            System.out.println("Evenement non trouvée");
        }
        return Res;
    }
    @GetMapping("/getAssociations")
    public void excelReader2(@RequestParam("file") MultipartFile excel) throws IOException {
        int i = 1;
        XSSFWorkbook workbook = new XSSFWorkbook(excel.getInputStream());
        XSSFSheet sheet1 = workbook.getSheetAt(0);
        while (i < sheet1.getPhysicalNumberOfRows()) {
            System.out.println("Loading Row " + i);
            XSSFRow row = sheet1.getRow(i);
            if (AR.findById(row.getCell(0).toString()).isEmpty()) {
                String Association = row.getCell(0).toString();
                String Reference = row.getCell(1).toString();
                String referenceConvention = row.getCell(2).toString();
                String EvenementDescription = row.getCell(3).toString();
                System.out.println("********************************************");
                System.out.println("Evenement Description" + EvenementDescription);
                Associations A = new Associations();
                A.setLibelle(Association);
                A.setReference(Reference);
                if (AddAssociation(A)) {
                    Conventions C = new Conventions();
                    C.setIdassoc(A.getReference());
                    C.setReferenceconv(referenceConvention);
                    if (AddConvention(C)) {
                        System.out.println("Convention Saved");
                        XSSFSheet EventSheet = workbook.getSheetAt(1);
                        for (int k = 1; k < EventSheet.getPhysicalNumberOfRows(); k++) {
                            XSSFRow row2 = EventSheet.getRow(k);
                            if (row2.getCell(0).toString().equals(EvenementDescription)) {
                                System.out.println("Evemenement Found");
                                String CodeEvenement = row2.getCell(1).toString();
                                int Prix = 150;
                                int places = 10;
                                String Localisation = row2.getCell(4).toString();
                                int promotion = 20;
                                 String DateDebut = "12/12/2019";
                                String DateFin = "12/12/2019";
                                int Det = (int) (Math.random() * 100000);
                                Detailevenement DE = new Detailevenement(Det,Localisation, Prix, promotion, places,  DateDebut,  DateFin);
                                if (AddDetailEvent(DE)) {
                                    System.out.println("Detail Evenement Ajouté");
                                    Evenement E = new Evenement();
                                    E.setDetails(DE.getIdetail());
                                    E.setRefconv(C.getReferenceconv());
                                    E.setCodevenement(CodeEvenement);
                                    E.setDescription(EvenementDescription);
                                    if (AddEvent(E)) {
                                        System.out.println("Evenement Ajouté");
                                        System.out.println("Finished");
                                    }
                                }
                            }
                        }
                    }
                  i++;
                }
            }else{
                System.out.println("Association déjà existante");
                i++;
            }
        }
    }

    public void DataExport(){
              // export all my database into a csv file
        DataFormatter df = new DataFormatter();
        StringBuilder sb = new StringBuilder();
        sb.append("Associations");
        sb.append("\n");
        for (Associations A : AR.findAll()) {
            sb.append(A.getLibelle());
            sb.append(",");
            sb.append(A.getReference());
            sb.append("\n");
        }
        sb.append("Conventions");
        sb.append("\n");
        for (Conventions C : CR.findAll()) {
            sb.append(C.getIdassoc());
            sb.append(",");
            sb.append(C.getReferenceconv());
            sb.append("\n");
        }
        sb.append("Detail Evenements");
        sb.append("\n");
        for (Detailevenement DE : ER.findAll()) {
            sb.append(DE.getIdetail());
            sb.append(",");
            sb.append(DE.getLocalisation());
            sb.append(",");
            sb.append(DE.getPrix());
            sb.append(",");
            sb.append(DE.getPromotion());
            sb.append(",");
            sb.append(DE.getPlaces());
            sb.append(",");
            sb.append(DE.getDatedeb());
            sb.append(",");
            sb.append(DE.getDatefin());
            sb.append("\n");
        }
        sb.append("Evenements");
        sb.append("\n");
        for (Evenement E : EE.findAll()) {
            sb.append(E.getCodevenement());
            sb.append(",");
            sb.append(E.getDescription());
            sb.append(",");
            sb.append(E.getRefconv());
            sb.append(",");
            sb.append(E.getDetails());
            sb.append("\n");
        }
        System.out.println(sb.toString());
        try {
            FileWriter writer = new FileWriter("C:\\Users\\HP\\Documents\\NetBeansProjects\\Association\\src\\main\\resources\\export.csv");
            writer.write(sb.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}