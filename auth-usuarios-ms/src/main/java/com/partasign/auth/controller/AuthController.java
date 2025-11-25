package com.partasign.auth.controller;

import com.partasign.auth.model.Usuario;
import com.partasign.auth.service.JwtService;
import com.partasign.auth.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth/Usuarios", description = "Autenticación y gestión de usuarios")
public class AuthController {

    private final UsuarioService service;
    private final JwtService jwtService;

    public AuthController(UsuarioService service, JwtService jwtService) {
        this.service = service;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    @Operation(summary = "Login de usuario y emisión de token")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String username = payload.get("username");
        String password = payload.get("password");
        Optional<Usuario> usuarioOpt = service.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        }
        Usuario usuario = usuarioOpt.get();
        if (!usuario.getPassword().equals(password) || !Boolean.TRUE.equals(usuario.getActivo())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales inválidas");
        }
        String token = jwtService.generateToken(usuario);
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("rol", usuario.getRol());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/usuarios")
    @Operation(summary = "Listar usuarios")
    public ResponseEntity<List<Usuario>> listar() {
        List<Usuario> usuarios = service.findAll();
        if (usuarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping("/usuarios")
    @Operation(summary = "Crear usuario")
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario) {
        Usuario creado = service.save(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @GetMapping("/usuarios/{id}")
    @Operation(summary = "Buscar usuario por id")
    public ResponseEntity<Usuario> buscar(@PathVariable Integer id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/usuarios/{id}")
    @Operation(summary = "Actualizar usuario")
    public ResponseEntity<Usuario> actualizar(@PathVariable Integer id, @RequestBody Usuario usuario) {
        Optional<Usuario> existenteOpt = service.findById(id);
        if (existenteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Usuario existente = existenteOpt.get();
        existente.setUsername(usuario.getUsername());
        existente.setPassword(usuario.getPassword());
        existente.setRol(usuario.getRol());
        existente.setActivo(usuario.getActivo());
        Usuario actualizado = service.save(existente);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/usuarios/{id}")
    @Operation(summary = "Eliminar usuario")
    public ResponseEntity<Void> eliminar(@PathVariable Integer id) {
        try {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

