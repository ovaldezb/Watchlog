package mx.com.watchlog.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BitacoraDTO {
    private String id;
    private String turno;
    private Date fecha;
    private String horaIncidente;
    private String tipoIncidente;
    private String descripcion;
    private String informacionAdicional;
}
