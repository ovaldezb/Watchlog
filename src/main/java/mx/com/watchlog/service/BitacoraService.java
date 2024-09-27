package mx.com.watchlog.service;

import mx.com.watchlog.dto.BitacoraDTO;
import mx.com.watchlog.entity.Bitacora;
import mx.com.watchlog.mapper.Mapper;
import mx.com.watchlog.repo.BitacoraRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BitacoraService {

    @Autowired
    BitacoraRepo bitacoraRepo;

    public ResponseEntity<List<BitacoraDTO>> getAllBitacoras(){
        List<Bitacora> listaBitacoras = bitacoraRepo.findAll();
        if(!listaBitacoras.isEmpty()) {
            return new ResponseEntity<>(listaBitacoras.stream().map(Mapper::bitacoraToBitacoraDTO).toList(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<BitacoraDTO> getBitacoraById(String idBitacora){
        Optional<Bitacora> bitacoraFound = bitacoraRepo.findById(idBitacora);
        return bitacoraFound.map(bitacora -> new ResponseEntity<>(Mapper.bitacoraToBitacoraDTO(bitacora), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<BitacoraDTO> addBitacora(BitacoraDTO bitacoraDTO){
        Bitacora bitacoraSaved = bitacoraRepo.save(Mapper.bitacoraDtoToBitacora(bitacoraDTO));
        return new ResponseEntity<>(Mapper.bitacoraToBitacoraDTO(bitacoraSaved),HttpStatus.CREATED);
    }

    public ResponseEntity<BitacoraDTO> updateBitacora(String idBitacora, BitacoraDTO bitacoraDTO){
        Optional<Bitacora> bitacoraUpdated = bitacoraRepo.findById(idBitacora);
        if(bitacoraUpdated.isPresent()){
            Bitacora bitacoraFound = bitacoraUpdated.get();
            bitacoraFound.setFecha(bitacoraDTO.getFecha());
            bitacoraFound.setDescripcion(bitacoraDTO.getDescripcion());
            bitacoraFound.setTurno(bitacoraDTO.getTurno());
            bitacoraFound.setHoraIncidente(bitacoraDTO.getHoraIncidente());
            bitacoraFound.setInformacionAdicional(bitacoraDTO.getInformacionAdicional());
            bitacoraFound.setTipoIncidente(bitacoraDTO.getTipoIncidente());
            return new ResponseEntity<>(Mapper.bitacoraToBitacoraDTO(bitacoraRepo.save(bitacoraFound)),HttpStatus.CREATED);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> deleteBitacora(String idBitacora){
        Optional<Bitacora> bitacora = bitacoraRepo.findById(idBitacora);
        if(bitacora.isPresent()) {
            bitacoraRepo.deleteById(idBitacora);
            return new ResponseEntity<>("Eliminado", HttpStatus.OK);
        }
        return new ResponseEntity<>("No encontrado",HttpStatus.NOT_FOUND);
    }
}
