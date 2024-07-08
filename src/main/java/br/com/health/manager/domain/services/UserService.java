package br.com.health.manager.domain.services;

import br.com.health.manager.domain.entities.user.UserEntity;
import br.com.health.manager.domain.entities.user.dtos.UsuarioAddDto;
import br.com.health.manager.domain.entities.user.dtos.UsuarioRetornoDto;
import br.com.health.manager.domain.repositories.UserRepository;
import br.com.health.manager.infra.exceptions.NotFoundException;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ViaCepService viaCepService;

    public UserService(UserRepository userRepository, ViaCepService viaCepService) {
        this.userRepository = userRepository;
        this.viaCepService = viaCepService;
    }

    @Transactional
    public UsuarioRetornoDto createUser(UsuarioAddDto usuarioAddDto){

        var cepservice = viaCepService.getEnderecoPorCep(usuarioAddDto.cep());
        if (cepservice.getSiafi() != null) {
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(usuarioAddDto, userEntity);
            userEntity.setLogradouro(cepservice.getLogradouro());
            userEntity.setComplemento(cepservice.getComplemento());
            userEntity.setBairro(cepservice.getBairro());
            userEntity.setLocalidade(cepservice.getLocalidade());
            userEntity.setUf(cepservice.getUf());
            userEntity.setIbge(cepservice.getIbge());
            userEntity.setGia(cepservice.getGia());
            userEntity.setDdd(cepservice.getDdd());
            userEntity.setSiafi(cepservice.getSiafi());

            var userSaved = userRepository.save(userEntity);
            return new UsuarioRetornoDto(userSaved);

        } else {
            throw new NotFoundException("Cep nao encontrado");
        }
    }

    @Transactional(readOnly = true)
    public UsuarioRetornoDto findOneUser(Long id){
        Optional<UserEntity> user = userRepository.findById(id);
        if(user.isPresent()){
            return new UsuarioRetornoDto(user.get());
        } else {
            throw new NotFoundException("Usuario nao encontrado");
        }
    }

    @Transactional(readOnly = true)
    public Page<UsuarioRetornoDto> findAllUsers(Pageable pageable){
        Page<UserEntity> users = userRepository.findAll(pageable);
        return users.map(UsuarioRetornoDto::new);
    }

    @Transactional
    public UsuarioRetornoDto updateUser(Long id, UsuarioAddDto usuarioAddDto){
        Optional<UserEntity> user = userRepository.findById(id);

        if (user.isPresent()){
            UserEntity userEntity = user.get();
            if (usuarioAddDto.cep() != user.get().getCep()){

                BeanUtils.copyProperties(usuarioAddDto, userEntity);

                var cepservice = viaCepService.getEnderecoPorCep(usuarioAddDto.cep());
                userEntity.setLogradouro(cepservice.getLogradouro());
                userEntity.setComplemento(cepservice.getComplemento());
                userEntity.setBairro(cepservice.getBairro());
                userEntity.setLocalidade(cepservice.getLocalidade());
                userEntity.setUf(cepservice.getUf());
                userEntity.setIbge(cepservice.getIbge());
                userEntity.setGia(cepservice.getGia());
                userEntity.setDdd(cepservice.getDdd());
                userEntity.setSiafi(cepservice.getSiafi());

                var userSaved = userRepository.save(userEntity);

                return new UsuarioRetornoDto(userSaved);
            } else {

                BeanUtils.copyProperties(usuarioAddDto, userEntity);
                var userSaved = userRepository.save(userEntity);
                return new UsuarioRetornoDto(userSaved);

            }
        } else {
            throw new NotFoundException("Usuario nao encontrado");
        }
    }












}
