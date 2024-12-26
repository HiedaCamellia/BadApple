#version 150

uniform sampler2D DiffuseSampler;
uniform float contrast;
uniform int reverse;

in vec2 texCoord;

out vec4 fragColor;

void main() {
    vec4 color = texture(DiffuseSampler, texCoord);
    float mix = (color.r + color.g + color.b) / 3.0;

    mix = mix * contrast + (1.0 - contrast) * 0.5;

    if(reverse==1) {
        mix = 1.0 - mix;
    }

    fragColor = vec4(mix, mix, mix, 1.0);
}
