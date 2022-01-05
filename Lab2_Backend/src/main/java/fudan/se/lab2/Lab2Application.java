package fudan.se.lab2;

import fudan.se.lab2.domain.Bed;
import fudan.se.lab2.domain.User;

import fudan.se.lab2.repository.BedRepository;
import fudan.se.lab2.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Welcome to 2020 Software Engineering Lab2.
 * This is your first lab to write your own code and build a spring boot application.
 * Enjoy it :)
 *
 * @author LBW
 */
@SpringBootApplication
public class Lab2Application {

    public static void main(String[] args) {
        SpringApplication.run(Lab2Application.class, args);
    }

    /**
     * This is a function to create some basic entities when the application starts.
     */
    @Bean
    public CommandLineRunner dataLoader(BedRepository bedRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {


                //Create some initial data if not exists.
                //初始化主治医生
                if (userRepository.findByUsername("doctor0") == null) {
                    User docter0 = new User(
                            "doctor0",
                            "password",
                            "doctor0",
                            0,
                            0,
                            0,
                            "000"
                    );
                    userRepository.save(docter0);

                    User docter1 = new User(
                            "doctor1",
                            "password",
                            "doctor1",
                            0,
                            1,
                            0,
                            "111"
                    );
                    userRepository.save(docter1);

                    User docter2 = new User(
                            "doctor2",
                            "password",
                            "doctor2",
                            0,
                            2,
                            0,
                            "222"
                    );
                    userRepository.save(docter2);
                }

                //初始化护士长
                if (userRepository.findByUsername("mainnurse0") == null) {
                    User mainnurse0 = new User(
                            "mainnurse0",
                            "password",
                            "mainnurse0",
                            1,
                            0,
                            1,
                            "000"
                    );
                    userRepository.save(mainnurse0);

                    User mainnurse1 = new User(
                            "mainnurse1",
                            "password",
                            "mainnurse1",
                            1,
                            1,
                            1,
                            "111"
                    );
                    userRepository.save(mainnurse1);

                    User mainnurse2 = new User(
                            "mainnurse2",
                            "password",
                            "mainnurse2",
                            1,
                            2,
                            1,
                            "222"
                    );
                    userRepository.save(mainnurse2);

                }


                //初始化急诊护士
                if (userRepository.findByUsername("emergency") == null) {
                    User emergency = new User(
                            "emergency",
                            "password",
                            "emergency",
                            1,
                            4,
                            2,
                            "222"
                    );
                    userRepository.save(emergency);
                }

                //初始化病房护士
                if (userRepository.findByUsername("sickroom00") == null) {
                    User sickroom00 = new User(
                            "sickroom00",
                            "password",
                            "sickroom00",
                            0,
                            0,
                            3,
                            "00"
                    );
                    userRepository.save(sickroom00);

                    User sickroom01 = new User(
                            "sickroom01",
                            "password",
                            "sickroom01",
                            0,
                            0,
                            3,
                            "01"
                    );
                    userRepository.save(sickroom01);

                    User sickroom1 = new User(
                            "sickroom1",
                            "password",
                            "sickroom1",
                            0,
                            1,
                            3,
                            "1"
                    );
                    userRepository.save(sickroom1);

                    User sickroom2 = new User(
                            "sickroom2",
                            "password",
                            "sickroom2",
                            0,
                            2,
                            3,
                            "2"
                    );
                    userRepository.save(sickroom2);
                }

                //初始化病床
                if (bedRepository.count() == 0) {
                    Bed bed00 = new Bed(
                            0,
                            0,
                            0
                    );
                    bedRepository.save(bed00);
                    Bed bed01 = new Bed(
                            0,
                            0,
                            1
                    );
                    bedRepository.save(bed01);
                    Bed bed02 = new Bed(
                            0,
                            0,
                            2
                    );
                    bedRepository.save(bed02);
                    Bed bed03 = new Bed(
                            0,
                            0,
                            3
                    );
                    bedRepository.save(bed03);
                    Bed bed10 = new Bed(
                            1,
                            0,
                            0
                    );
                    bedRepository.save(bed10);
                    Bed bed11 = new Bed(
                            1,
                            0,
                            1
                    );
                    bedRepository.save(bed11);
                    Bed bed20 = new Bed(
                            2,
                            0,
                            0
                    );
                    bedRepository.save(bed20);
                }
            }
        };
    }
}

