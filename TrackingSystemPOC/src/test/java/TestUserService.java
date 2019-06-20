import com.packs.TrackingSystemPOC.entity.Users;
import com.packs.TrackingSystemPOC.repositories.UsersRepository;
import com.packs.TrackingSystemPOC.services.UsersService;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class TestUserService {

    @InjectMocks
    UsersService usersService;

    @Mock
    UsersRepository usersRepository;

    @Mock
    Users users;

    @Mock
    List<Users> usersList;

    int userId;

    @Test
    public void saveUserTest(){
        Mockito.when(usersRepository.save(users)).thenReturn(users);
        Assert.assertThat(usersService.addUser(users), Is.is(users));
        Mockito.verify(usersRepository,Mockito.times(1)).save(users);
    }

    @Test
    public void getAllUserTest(){
        Mockito.when(usersRepository.findAll()).thenReturn(usersList);
        Assert.assertThat(usersService.getAllUsers(),Is.is(usersList));
        Mockito.verify(usersRepository,Mockito.times(1)).findAll();
    }

    @Test
    public void getUserTest(){
        Mockito.when(usersRepository.findById(userId)).thenReturn(java.util.Optional.of(users));
        Assert.assertThat(usersService.getUser(userId),Is.is(java.util.Optional.of(users)));
        Mockito.verify(usersRepository,Mockito.times(1)).findById(userId);
    }

    @Test
    public void  deleteUserTest(){
        Assert.assertThat(usersService.deleteUser(userId),Is.is(userId));
        Mockito.verify(usersRepository,Mockito.times(1)).deleteById(userId);
    }
}
