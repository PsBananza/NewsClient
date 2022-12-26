package com.dmitry.NewsClient.service;

import lombok.RequiredArgsConstructor;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;
import java.util.stream.IntStream;

/**
 * Класс для выгрузки файла с инф по купленным курсам, для CA и SA
 *
 * @author Spicin DN
 */
@Component
@RequiredArgsConstructor
public class GetUploadingPurchase {

    public ResponseEntity<byte[]> uploadPurchaseResultsForSa() throws IOException, ParseException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(" Promocode");

        HSSFRow row;
        int q = 0;

        Map<String, Object[]> promocodeInfo = new TreeMap<>();

        promocodeInfo.put(String.valueOf(q++), new Object[]{
                "Payment date",
                "Money paid",
                "User name",
        });

        Set<String> keyid = promocodeInfo.keySet();

        int rowid = 0;

        for (String key : keyid) {

            row = sheet.createRow(rowid++);
            Object[] objectArr = promocodeInfo.get(key);
            int cellid = 0;

            for (Object obj : objectArr) {
                Cell cell = row.createCell(cellid++);
                cell.setCellValue((String.valueOf(obj)));
            }
        }
        autoSizeColumns(sheet);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            workbook.write(bos);
        } finally {
            bos.close();
        }
        byte[] bytes = bos.toByteArray();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"report.xls\"")
                .body(bytes);
    }

    private void autoSizeColumns(HSSFSheet sheet) {
        IntStream.rangeClosed(0, 21).forEach(sheet::autoSizeColumn);
    }
}
