#version 330
out vec4 frag_color;
uniform vec4 uni_color;
// in -> mendapat data dari luar frag
// out -> mengeluarkan data dari frag

void main() {
    //frag_color = vec4(1.0, 0.0, 0.0, 1.0);
    frag_color = uni_color;
}