#version 430

in vec4 vfColor;
out vec4 fColor;

struct PositionalLight
{
    vec4 ambient;
    vec4 diffuse;
    vec4 specular;
    vec3 position;
};

struct Material
{
    vec4 color;
    float shininess;
};

uniform vec4 globalAmbient;
uniform PositionalLight light;
uniform Material material;
uniform mat4 pMat;
uniform mat4 mvMat;
uniform mat4 nMat;

void main() {
    fColor = vfColor;
}
