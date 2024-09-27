package mx.com.watchlog.controller;

import mx.com.watchlog.dto.BitacoraDTO;
import mx.com.watchlog.entity.Bitacora;
import mx.com.watchlog.service.BitacoraService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BitacoraControllerTest {

    @Mock
    BitacoraService bitacoraService;

    @InjectMocks
    BitacoraController bitacoraController;

    Bitacora bitacora1 = Bitacora.builder()
            .id("1")
            .fecha(new Date())
            .turno("matutino")
            .horaIncidente("11:20")
            .descripcion("incidente 1")
            .tipoIncidente("vandalismo")
            .informacionAdicional("n/a")
            .build();
    BitacoraDTO bitacoraDTO1 = BitacoraDTO.builder()
            .id("1")
            .fecha(new Date())
            .turno("matutino")
            .horaIncidente("11:20")
            .descripcion("incidente 1")
            .tipoIncidente("vandalismo")
            .informacionAdicional("n/a")
            .build();
    BitacoraDTO bitacoraDTO2 = BitacoraDTO.builder()
            .id("2")
            .fecha(new Date())
            .turno("vespertino")
            .horaIncidente("12:20")
            .descripcion("incidente 2")
            .tipoIncidente("vandalismo")
            .informacionAdicional("n/a")
            .build();
    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAllBitacorasTest(){
        List<BitacoraDTO> listaBitacorasDTO = Arrays.asList(bitacoraDTO1,bitacoraDTO2);
        ResponseEntity<List<BitacoraDTO>> responseEntityResp = new ResponseEntity<>(listaBitacorasDTO, HttpStatus.OK);
        when(bitacoraService.getAllBitacoras()).thenReturn(responseEntityResp);
        ResponseEntity<List<BitacoraDTO>> allBitacoras = bitacoraController.getAllBitacoras();
        assertEquals(responseEntityResp,allBitacoras);
        verify(bitacoraService,times(1)).getAllBitacoras();
    }

    @Test
    public void getBitacoraByIdTest(){
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity = new ResponseEntity<>(bitacoraDTO1,HttpStatus.OK);
        when(bitacoraService.getBitacoraById(any())).thenReturn(bitacoraDTOResponseEntity);
        ResponseEntity<BitacoraDTO> bitacoraById = bitacoraController.getBitacoraById("1");
        assertEquals(bitacoraDTOResponseEntity,bitacoraById);
        verify(bitacoraService,times(1)).getBitacoraById("1");
    }

    @Test
    public void addBitacoraTest(){
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity = new ResponseEntity<>(bitacoraDTO1,HttpStatus.CREATED);
        when(bitacoraService.addBitacora(bitacoraDTO1)).thenReturn(bitacoraDTOResponseEntity);
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity1 = bitacoraController.addBitacora(bitacoraDTO1);
        assertEquals(bitacoraDTOResponseEntity,bitacoraDTOResponseEntity1);
        verify(bitacoraService,times(1)).addBitacora(bitacoraDTO1);
    }

    @Test
    public void updateBitacoraTest(){
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity = new ResponseEntity<>(bitacoraDTO2,HttpStatus.OK);
        when(bitacoraService.updateBitacora(any(),any())).thenReturn(bitacoraDTOResponseEntity);
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity1 = bitacoraController.updateBitacora("2", bitacoraDTO2);
        assertEquals(bitacoraDTOResponseEntity,bitacoraDTOResponseEntity1);
        verify(bitacoraService,times(1)).updateBitacora("2",bitacoraDTO2);
    }

    @Test
    public void deleteBitacoraTest(){
        ResponseEntity<String> deleteResponse = new ResponseEntity<>("Eliminado",HttpStatus.OK);
        when(bitacoraService.deleteBitacora(any())).thenReturn(deleteResponse);
        ResponseEntity<?> responseEntity = bitacoraController.deleteBitacora("1");
        assertEquals(responseEntity,deleteResponse);
        verify(bitacoraService,times(1)).deleteBitacora("1");
    }
}
