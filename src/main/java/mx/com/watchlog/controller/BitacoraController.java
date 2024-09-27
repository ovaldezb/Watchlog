package mx.com.watchlog.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import mx.com.watchlog.dto.BitacoraDTO;
import mx.com.watchlog.service.BitacoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bitacora")
@Tag(name = "Watch Log CRUD", description = "endpoints to perform CRUD operations")
public class BitacoraController {
    @Autowired
    BitacoraService bitacoraService;

    @Operation(summary = "Get all bitacoras", description = "Get a list of all available bitacora")
    @ApiResponses({
            @ApiResponse(responseCode = "302", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", content = {@Content(mediaType = "application/json")})
    })
    @GetMapping
    public ResponseEntity<List<BitacoraDTO>> getAllBitacoras(){
        return bitacoraService.getAllBitacoras();
    }

    @Operation(summary = "Get bitacora by Id", description = "Get a bitacora by its Id and return a BitacoraDTO object")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = BitacoraDTO.class),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema())})
    })
    @GetMapping("/{idBitacora}")
    public ResponseEntity<BitacoraDTO> getBitacoraById(@PathVariable String idBitacora){
        return bitacoraService.getBitacoraById(idBitacora);
    }

    @Operation(summary = "Add bitacora", description = "Receive a BotacoraDTO object and creates a new bitacora ")
    @ApiResponses({
            @ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "400")
    })
    @PostMapping
    public ResponseEntity<BitacoraDTO> addBitacora(@RequestBody BitacoraDTO bitacora){
        return bitacoraService.addBitacora(bitacora);
    }

    @Operation(summary = "Update bitacora", description = "Receives a BitacoraDTO object and a Bitacora Id to update the found bitacora object")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = {@Content(schema = @Schema(),mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema(),mediaType = "application/json")}),
    })
    @PutMapping("/{idBitacora}")
    public ResponseEntity<BitacoraDTO> updateBitacora(@PathVariable String idBitacora, @RequestBody BitacoraDTO bitacoraDTO){
        return bitacoraService.updateBitacora(idBitacora,bitacoraDTO);
    }

    @Operation(summary = "Delete bitacora", description = "Find Bitacora by Id and if found, it is deleted, delete id physical")
    @DeleteMapping("/{idBitacora}")
    public ResponseEntity<?> deleteBitacora(@PathVariable String idBitacora){
        return bitacoraService.deleteBitacora(idBitacora);
    }

}
