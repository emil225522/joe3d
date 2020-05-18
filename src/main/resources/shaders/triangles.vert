#version 430

layout (location=0) in vec3 position;
layout (location=1) in vec2 tex;
layout (location=2) in vec3 normal;

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

uniform vec4 global_ambient;
uniform PositionalLight light;
uniform Material material;
uniform mat4 proj_mat;
uniform mat4 mv_mat;
uniform mat4 n_mat;

out vec4 vfColor;

void main() {
    vec4 P = mv_mat * vec4(position, 1.0);
    vec3 N = normalize((n_mat * vec4(normal, 1.0)).xyz);
    vec3 L = normalize(light.position - P.xyz);
    vec3 V = normalize(-P.xyz);
    vec3 R = reflect(-L,N);

    // ambient, diffuse, and specular contributions
    vec3 ambient = ((global_ambient * material.color) + (light.ambient * material.color)).xyz;
    vec3 diffuse = light.diffuse.xyz * material.color.xyz * max(dot(N,L), 0.0);
    vec3 specular = material.color.xyz * light.specular.xyz * pow(max(dot(R,V), 0.0f), material.shininess);

    vfColor = vec4((ambient+diffuse+specular), 1.0);
    gl_Position = proj_mat * mv_mat * vec4(position, 1.0);
}
