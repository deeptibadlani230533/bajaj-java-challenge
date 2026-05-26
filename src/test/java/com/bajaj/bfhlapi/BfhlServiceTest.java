package com.bajaj.bfhlapi;

import com.bajaj.bfhlapi.dto.RequestDTO;
import com.bajaj.bfhlapi.dto.ResponseDTO;
import com.bajaj.bfhlapi.service.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BfhlServiceTest {

    private BfhlServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new BfhlServiceImpl();
    }

    @Test
    void testProcessData_withSampleInput() {
        // Input: ["a", "1", "334", "4", "R", "$"]
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList("a", "1", "334", "4", "R", "$"));

        ResponseDTO resp = service.processData(req);

        assertTrue(resp.isSuccess());
        assertEquals("deepti_badlani_11032005", resp.getUserId());
        assertEquals("deeptibadlani230533@acropolis.in", resp.getEmail());
        assertEquals("0827CS231074", resp.getRollNumber());

        // odd numbers
        assertEquals(List.of("1"), resp.getOddNumbers());

        // even numbers
        assertEquals(List.of("334", "4"), resp.getEvenNumbers());

        // alphabets uppercase
        assertTrue(resp.getAlphabets().contains("A"));
        assertTrue(resp.getAlphabets().contains("R"));

        // special chars
        assertEquals(List.of("$"), resp.getSpecialCharacters());

        // sum = 1 + 334 + 4 = 339
        assertEquals("339", resp.getSum());

        // concat_string: "a","R" -> combined "aR" -> reversed "Ra" -> alternating "Ra"
        assertEquals("Ra", resp.getConcatString());
    }

    @Test
    void testProcessData_emptyData() {
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList());

        ResponseDTO resp = service.processData(req);

        assertTrue(resp.isSuccess());
        assertTrue(resp.getOddNumbers().isEmpty());
        assertTrue(resp.getEvenNumbers().isEmpty());
        assertTrue(resp.getAlphabets().isEmpty());
        assertTrue(resp.getSpecialCharacters().isEmpty());
        assertEquals("0", resp.getSum());
        assertEquals("", resp.getConcatString());
    }

    @Test
    void testProcessData_multiCharAlpha() {
        // Input has multi-char alphabetic strings
        // "A","ABCD","DOE" -> all chars "AABCDDOE" -> reversed "EODDCBAA" -> alternating "EoDdCbAa"
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList("A", "ABCD", "DOE"));

        ResponseDTO resp = service.processData(req);

        assertEquals("EoDdCbAa", resp.getConcatString());
        assertEquals("0", resp.getSum());
        assertTrue(resp.getOddNumbers().isEmpty());
        assertTrue(resp.getEvenNumbers().isEmpty());
        assertTrue(resp.getSpecialCharacters().isEmpty());
    }

    @Test
    void testProcessData_alternatingCaps_threeItems() {
        // "a","y","b" -> combined "ayb" -> reversed "bya" -> alternating "ByA"
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList("a", "y", "b"));

        ResponseDTO resp = service.processData(req);

        assertEquals("ByA", resp.getConcatString());
    }

    @Test
    void testProcessData_onlyNumbers() {
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList("2", "3", "10", "7"));

        ResponseDTO resp = service.processData(req);

        assertTrue(resp.isSuccess());
        assertEquals(List.of("3", "7"), resp.getOddNumbers());
        assertEquals(List.of("2", "10"), resp.getEvenNumbers());
        assertTrue(resp.getAlphabets().isEmpty());
        assertTrue(resp.getSpecialCharacters().isEmpty());
        assertEquals("22", resp.getSum());  // 2+3+10+7 = 22
    }

    @Test
    void testProcessData_onlySpecialChars() {
        RequestDTO req = new RequestDTO();
        req.setData(Arrays.asList("$", "&", "*"));

        ResponseDTO resp = service.processData(req);

        assertTrue(resp.isSuccess());
        assertTrue(resp.getOddNumbers().isEmpty());
        assertTrue(resp.getEvenNumbers().isEmpty());
        assertTrue(resp.getAlphabets().isEmpty());
        assertEquals(List.of("$", "&", "*"), resp.getSpecialCharacters());
        assertEquals("0", resp.getSum());
        assertEquals("", resp.getConcatString());
    }
}
