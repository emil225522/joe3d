#version 430
/**
*   Blinn-Phong fragment shader
*/

in vec3 varyingNormal;
in vec3 varyingLightDir;
in vec3 varyingVertPos;
in vec3 varyingHalfVector;
in vec2 fTexCoord;
out vec4 color;

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

void main() {
    vec3 lightDir = normalize(varyingLightDir);
    vec3 normal = normalize(varyingNormal);
    vec3 vertPos = normalize(-varyingVertPos);
    vec3 halfwayDir = normalize(varyingHalfVector);
    // get the angle between the light and surface normal:
    float cosTheta = dot(lightDir, normal);
    // angle between the view vector and reflected light:
    float cosPhi = dot(normal, halfwayDir);

    vec3 ambient = (material.color * (global_ambient + light.ambient)).xyz;
    vec3 diffuse = light.diffuse.xyz * material.color.xyz * max(cosTheta,0.0);
    vec3 specular = light.specular.xyz * material.color.xyz * pow(max(cosPhi,0.0), material.shininess*3.0);     // tweak for Blinn extension of Phong shading

    color = vec4((ambient+diffuse+specular),1.0);
}
