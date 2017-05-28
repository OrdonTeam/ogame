package io.github.ordonteam.ogame.script

import org.junit.Assert.assertNotNull
import org.junit.Test

class CheckEnvVariable {

    @Test
    fun shouldFindLoginAndPasswordEnvironmentVariables() {
        System.getenv().forEach{key, value ->
            System.err.println("$key $value")
        }
    }
}