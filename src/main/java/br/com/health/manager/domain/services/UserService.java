package br.com.health.manager.domain.services;

import br.com.health.manager.domain.entities.user.UserEntity;
import br.com.health.manager.domain.entities.user.dtos.UsuarioAddDto;
import br.com.health.manager.domain.entities.user.dtos.UsuarioRetornoDto;
import br.com.health.manager.domain.repositories.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final ViaCepService viaCepService;

    public UserService(UserRepository userRepository, ViaCepService viaCepService) {
        this.userRepository = userRepository;
        this.viaCepService = viaCepService;
    }

    public UsuarioRetornoDto createUser(UsuarioAddDto usuarioAddDto){
        var cepservice = viaCepService.getEnderecoPorCep(usuarioAddDto.cep());
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
    }
}
