#version 430

uniform mat4 pMat;
uniform mat4 mvMat;
uniform vec4 color;

layout (location=0) in vec3 pos;

out vec4 fColor;

void main() {
    gl_Position = pMat * mvMat * vec4(pos, 1.0);
    fColor = color;
}
