package utility;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static utility.Const.MESHES;

class ColladaParserTest {
    ColladaParser parser = new ColladaParser();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void parse() {
        parser.parse(MESHES + "cube.dae");
    }
}