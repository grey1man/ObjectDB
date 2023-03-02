package config;

import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.persist.EntityStore;
import com.sleepycat.persist.StoreConfig;

import java.io.File;

public class Database {
    // Файл с базой данных
    private static final File envHome = new File("./JEDB");
    private static Environment environment;
    private static EntityStore store;

    public static void setup() {
        // Конфиг окружения
        EnvironmentConfig envConfig = new EnvironmentConfig();
        // Конфиг хранилища
        StoreConfig storeConfig = new StoreConfig();

        // Выполняем настройки
        envConfig.setAllowCreate(true);
        storeConfig.setAllowCreate(true);

        // Открыть окружение
        Database.environment = new Environment(envHome, envConfig);
        // Открыть хранилище
        Database.store = new EntityStore(environment, "EntityStore", storeConfig);
    }

    // Закрыть окружение и хранилище
    public static void shutdown()
            throws DatabaseException {

        store.close();
        environment.close();
    }

    public static File getEnvHome() {
        return envHome;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    public static EntityStore getStore() {
        return store;
    }
}
