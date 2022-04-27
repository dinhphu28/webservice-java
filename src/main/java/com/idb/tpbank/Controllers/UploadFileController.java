package com.idb.tpbank.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idb.tpbank.Models.Animal;
import com.idb.tpbank.Models.KhachHang;
import com.idb.tpbank.Services.ApiService;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/test-sales")
public class UploadFileController {
    @Autowired
    private ApiService apiService;

    @GetMapping(
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Object> testGet() {
        return new ResponseEntity<>("{ \"notice\": \"OK\" }", HttpStatus.OK);
    }

    @PostMapping(
        value = "/test",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<Object> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        ResponseEntity<Object> entity;

        List<Animal> tmpAnimals = new ArrayList<Animal>();

        Boolean isSuccess = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for(int ii = 0; ii < worksheet.getPhysicalNumberOfRows(); ii++) {
                Animal tmpAnimal = new Animal();

                XSSFRow row = worksheet.getRow(ii);

                tmpAnimal.setName(row.getCell(0).getStringCellValue());
                tmpAnimal.setAge((int)row.getCell(1).getNumericCellValue());

                tmpAnimals.add(tmpAnimal);

                isSuccess = true;
            }
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(isSuccess) {
            entity = new ResponseEntity<>(tmpAnimals, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("{ \"notice\": \"failed\" }", HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping(
        value = "/import",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.MULTIPART_FORM_DATA_VALUE}
    )
    public ResponseEntity<Object> uploadFileImport(@RequestParam(value = "file", required = true) MultipartFile file,
                @RequestParam(value = "id", required = false) String id,
                // @RequestParam(value = "url", required = false) String url,
                @RequestParam(value = "user", required = false) String user,
                @RequestParam(value = "fullname", required = false) String fullname,
                @RequestParam(value = "type", required = false) String type,
                @RequestParam(value = "ghiChu", required = false) String ghiChu,
                @RequestParam(value = "nguon", required = false) String nguon,
                @RequestParam(value = "fkChienDich", required = false) String fkChienDich) throws IOException {
        ResponseEntity<Object> entity;

        List<KhachHang> khachHangs = new ArrayList<KhachHang>();

        Boolean isSuccess = false;
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream());
            XSSFSheet worksheet = workbook.getSheetAt(0);

            for(int ii = 1; ii < worksheet.getPhysicalNumberOfRows(); ii++) {
                KhachHang khachHang = new KhachHang();

                XSSFRow row = worksheet.getRow(ii);

                for(int jj = 0; jj < row.getPhysicalNumberOfCells(); jj++) {
                    row.getCell(jj).setCellType(CellType.STRING);
                }

                khachHang.setMonthz(row.getCell(0).getStringCellValue());
                khachHang.setSys(row.getCell(1).getStringCellValue());
                khachHang.setAppnum(row.getCell(2).getStringCellValue());
                khachHang.setLoanid(row.getCell(3).getStringCellValue());
                khachHang.setLoanaccountno(row.getCell(4).getStringCellValue());
                khachHang.setDpdbom(row.getCell(5).getStringCellValue());
                khachHang.setOverdueprinamt(row.getCell(6).getStringCellValue());
                khachHang.setOverdueintamt(row.getCell(7).getStringCellValue());
                khachHang.setOverduelpfamt(row.getCell(8).getStringCellValue());
                khachHang.setNextcycleduedate(row.getCell(9).getStringCellValue());
                khachHang.setNextcycledueamount(row.getCell(10).getStringCellValue());
                khachHang.setCustomermobilenum(row.getCell(11).getStringCellValue());
                khachHang.setActive(row.getCell(12).getStringCellValue());
                khachHang.setObsduedate(row.getCell(13).getStringCellValue());
                khachHang.setObsdueno(row.getCell(14).getStringCellValue());
                khachHang.setAssigninvaliddate(row.getCell(15).getStringCellValue());
                khachHang.setFutureprinamt(row.getCell(16).getStringCellValue());
                khachHang.setPermfulladdr(row.getCell(17).getStringCellValue());
                khachHang.setPermregion(row.getCell(18).getStringCellValue());
                khachHang.setPermcity(row.getCell(19).getStringCellValue());
                khachHang.setPermarea(row.getCell(20).getStringCellValue());
                khachHang.setCurrfulladdr(row.getCell(21).getStringCellValue());
                khachHang.setCurrcity(row.getCell(22).getStringCellValue());
                khachHang.setCurrarea(row.getCell(23).getStringCellValue());
                khachHang.setPosbom(row.getCell(24).getStringCellValue());
                khachHang.setPosassign(row.getCell(25).getStringCellValue());
                khachHang.setRemainprin(row.getCell(26).getStringCellValue());
                khachHang.setCustomername(row.getCell(27).getStringCellValue());
                khachHang.setBirthday(row.getCell(28).getStringCellValue());
                khachHang.setEmi(row.getCell(29).getStringCellValue());
                khachHang.setGender(row.getCell(30).getStringCellValue());
                khachHang.setSpousename(row.getCell(31).getStringCellValue());
                khachHang.setSpousephone(row.getCell(32).getStringCellValue());
                khachHang.setFamilyphone(row.getCell(33).getStringCellValue());
                khachHang.setFamilybook(row.getCell(34).getStringCellValue());
                khachHang.setFamilyrelation(row.getCell(35).getStringCellValue());
                khachHang.setRefname1(row.getCell(36).getStringCellValue());
                khachHang.setRefrelationship1(row.getCell(37).getStringCellValue());
                khachHang.setPhoneref1(row.getCell(38).getStringCellValue());
                khachHang.setRefname2(row.getCell(39).getStringCellValue());
                khachHang.setRefrelationship2(row.getCell(40).getStringCellValue());
                khachHang.setPhoneref2(row.getCell(41).getStringCellValue());
                khachHang.setWorkphone(row.getCell(42).getStringCellValue());
                khachHang.setAddname1(row.getCell(43).getStringCellValue());
                khachHang.setAddphone1(row.getCell(44).getStringCellValue());
                khachHang.setAddphone2(row.getCell(45).getStringCellValue());
                khachHang.setCif(row.getCell(46).getStringCellValue());
                khachHang.setPernamentaddress(row.getCell(47).getStringCellValue());
                khachHang.setDomicileaddress(row.getCell(48).getStringCellValue());
                khachHang.setCompanyname(row.getCell(49).getStringCellValue());
                khachHang.setTenor(row.getCell(50).getStringCellValue());
                khachHang.setLoanamt(row.getCell(51).getStringCellValue());
                khachHang.setDisbursementdate(row.getCell(52).getStringCellValue());
                khachHang.setAddname2(row.getCell(53).getStringCellValue());
                khachHang.setProductkh(row.getCell(54).getStringCellValue());
                khachHang.setContractdate(row.getCell(55).getStringCellValue());
                khachHang.setIdcard(row.getCell(56).getStringCellValue());
                khachHang.setCurraccount(row.getCell(57).getStringCellValue());
                khachHang.setFirstpaiddate(row.getCell(58).getStringCellValue());
                khachHang.setLastpaiddate(row.getCell(59).getStringCellValue());
                khachHang.setLastcallfinal(row.getCell(60).getStringCellValue());
                khachHang.setLastresultfinal(row.getCell(61).getStringCellValue());
                khachHang.setTotalamtpaid(row.getCell(62).getStringCellValue());
                khachHang.setTotaloverdue(row.getCell(63).getStringCellValue());
                khachHang.setDuedateoverdue(row.getCell(64).getStringCellValue());
                khachHang.setOffifulladdr(row.getCell(65).getStringCellValue());
                khachHang.setOffarea(row.getCell(66).getStringCellValue());
                khachHang.setOffcity(row.getCell(67).getStringCellValue());
                khachHang.setStk(row.getCell(68).getStringCellValue());
                khachHang.setGroupz(row.getCell(69).getStringCellValue());
                khachHang.setDpdcur(row.getCell(70).getStringCellValue());
                khachHang.setDpdassign(row.getCell(71).getStringCellValue());
                khachHang.setAssigndate(row.getCell(72).getStringCellValue());
                khachHang.setStatus(row.getCell(73).getStringCellValue());
                khachHang.setWriteoff(row.getCell(74).getStringCellValue());
                khachHang.setAgency(row.getCell(75).getStringCellValue());
                khachHang.setMob(row.getCell(76).getStringCellValue());
                khachHang.setAgent(row.getCell(77).getStringCellValue());
                
                khachHangs.add(khachHang);
            }

            ObjectMapper objectMapper = new ObjectMapper();

            try {
                String jsonStr = objectMapper.writeValueAsString(khachHangs);

                // System.out.println(jsonStr);

                apiService.impSource(jsonStr, user, fullname, nguon);

                isSuccess = true;
            } catch (Exception e) {
                //TODO: handle exception
            }

            // isSuccess = true;
        } catch (Exception e) {
            //TODO: handle exception
        }

        if(isSuccess) {
            entity = new ResponseEntity<>(khachHangs, HttpStatus.OK);
        } else {
            entity = new ResponseEntity<>("{ \"notice\": \"failed\" }", HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
