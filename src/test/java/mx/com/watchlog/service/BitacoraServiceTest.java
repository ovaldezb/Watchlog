package mx.com.watchlog.service;

import mx.com.watchlog.dto.BitacoraDTO;
import mx.com.watchlog.entity.Bitacora;
import mx.com.watchlog.mapper.Mapper;
import mx.com.watchlog.repo.BitacoraRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BitacoraServiceTest {

    @InjectMocks
    BitacoraService bitacoraService;

    @Mock
    BitacoraRepo bitacoraRepo;

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
    Bitacora bitacora2 = Bitacora.builder()
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
        List<Bitacora> listaRespuesta = Arrays.asList(bitacora1,bitacora2);
        ResponseEntity<List<BitacoraDTO>> listaRespBitDTO = new ResponseEntity<>(listaRespuesta.stream().map(Mapper::bitacoraToBitacoraDTO).toList(),HttpStatus.FOUND);
        when(bitacoraRepo.findAll()).thenReturn(listaRespuesta);
        ResponseEntity<List<BitacoraDTO>> allBitacoras = bitacoraService.getAllBitacoras();
        assertEquals(allBitacoras,listaRespBitDTO);
        verify(bitacoraRepo,times(1)).findAll();
    }

    @Test
    public void getAllBitacorasNotFoundTest(){
        ResponseEntity<List<BitacoraDTO>> listaRespBitDTO = new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
        List<Bitacora> arrayEmpty = new ArrayList<Bitacora>();
        when(bitacoraRepo.findAll()).thenReturn(arrayEmpty);
        ResponseEntity<List<BitacoraDTO>> allBitacoras = bitacoraService.getAllBitacoras();
        assertEquals(allBitacoras,listaRespBitDTO);
        verify(bitacoraRepo,times(1)).findAll();
    }

    @Test
    public void getBitacoraByIdTest(){
        ResponseEntity<BitacoraDTO> bitacoraDTOResp = new ResponseEntity<>(Mapper.bitacoraToBitacoraDTO(bitacora1), HttpStatus.OK);
        when(bitacoraRepo.findById("1")).thenReturn(Optional.ofNullable(bitacora1));
        ResponseEntity<BitacoraDTO> bitacoraById = bitacoraService.getBitacoraById("1");
        assertEquals(bitacoraById,bitacoraDTOResp);
    }

    @Test
    public void addBitacoraTest(){
        ResponseEntity<BitacoraDTO> responseBitacoraDTO = new ResponseEntity<>(bitacoraDTO1,HttpStatus.CREATED);
        when(bitacoraRepo.save(any())).thenReturn(bitacora1);
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity = bitacoraService.addBitacora(bitacoraDTO1);
        assertEquals(responseBitacoraDTO,bitacoraDTOResponseEntity);
    }

    @Test
    public void updateBitacoraTest(){
        ResponseEntity<BitacoraDTO> responseEntityDTO2 = new ResponseEntity<>(Mapper.bitacoraToBitacoraDTO(bitacora2),HttpStatus.CREATED);
        when(bitacoraRepo.findById("2")).thenReturn(Optional.ofNullable(bitacora2));
        when(bitacoraRepo.save(any())).thenReturn(bitacora2);
        ResponseEntity<BitacoraDTO> bitacoraDTOResponseEntity = bitacoraService.updateBitacora("2", Mapper.bitacoraToBitacoraDTO(bitacora2));
        assertEquals(responseEntityDTO2,bitacoraDTOResponseEntity);
    }

    @Test
    public void updateBitacoraNotFound(){
        ResponseEntity<BitacoraDTO> responseEntityResp = new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        when(bitacoraRepo.findById("3")).thenReturn(Optional.empty());
        ResponseEntity<BitacoraDTO> responseEntity = bitacoraService.updateBitacora("3", bitacoraDTO1);
        assertEquals(responseEntityResp,responseEntity);
        verify(bitacoraRepo,times(1)).findById("3");
    }

    @Test
    public void deleteByIdTest(){
        ResponseEntity<String > responseEntityResp = new ResponseEntity<>("Eliminado",HttpStatus.OK);
        ResponseEntity<String> stringResponseEntity = bitacoraService.deleteBitacora("1");
        verify(bitacoraRepo,times(1)).deleteById("1");
        assertEquals(responseEntityResp,stringResponseEntity);
    }
}
