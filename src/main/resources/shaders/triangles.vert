#version 430

layout (location=0) in vec3 pos;
uniform mat4 pMat;
uniform mat4 mvMat;

void main() {
    gl_Position = vec4(pos, 1.0);
}
