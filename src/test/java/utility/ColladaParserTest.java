package utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ColladaParserTest {

    @Test
    public void parseCollada(){
        ColladaData data = ColladaParser.parse(Const.MESHES+"cube.dae");
        System.out.println(data);
    }
}