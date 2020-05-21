#version 430
/**
*   Blinn-Phong vertex shader
*/
layout (location=0) in vec3 vertPos;
layout (location=1) in vec2 texCoord;
layout (location=2) in vec3 vertNormal;

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

uniform sampler2D tex;
uniform vec4 global_ambient;
uniform PositionalLight light;
uniform Material material;
uniform mat4 proj_mat, mv_mat, n_mat;

out vec3 varyingNormal;     // flat qualifier here flat shades the model
out vec3 varyingLightDir;
out vec3 varyingVertPos;
out vec3 varyingHalfVector;
out vec2 fTexCoord;

void main() {
    varyingVertPos=(mv_mat * vec4(vertPos,1.0)).xyz;
    varyingLightDir = light.position - varyingVertPos;
    varyingNormal = (n_mat * vec4(vertNormal,1.0)).xyz;
    varyingHalfVector = (varyingLightDir + (-varyingVertPos)).xyz;
    fTexCoord = texCoord;
    gl_Position = proj_mat * mv_mat * vec4(vertPos, 1.0);
}
