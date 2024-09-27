package mx.com.watchlog.mapper;

import mx.com.watchlog.entity.Bitacora;
import mx.com.watchlog.dto.BitacoraDTO;

public class Mapper {

    public static Bitacora bitacoraDtoToBitacora(BitacoraDTO bitacoraDTO){
        return Bitacora.builder()
                .id(bitacoraDTO.getId())
                .fecha(bitacoraDTO.getFecha())
                .horaIncidente(bitacoraDTO.getHoraIncidente())
                .descripcion(bitacoraDTO.getDescripcion())
                .turno(bitacoraDTO.getTurno())
                .tipoIncidente(bitacoraDTO.getTipoIncidente())
                .informacionAdicional(bitacoraDTO.getInformacionAdicional())
                .build();
    }

    public static BitacoraDTO bitacoraToBitacoraDTO(Bitacora bitacora){
        return BitacoraDTO.builder()
                .id(bitacora.getId())
                .fecha(bitacora.getFecha())
                .horaIncidente(bitacora.getHoraIncidente())
                .descripcion(bitacora.getDescripcion())
                .turno(bitacora.getTurno())
                .tipoIncidente(bitacora.getTipoIncidente())
                .informacionAdicional(bitacora.getInformacionAdicional())
                .build();
    }
}
