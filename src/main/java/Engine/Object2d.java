package Engine;

import org.joml.Vector3f;

import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Object2d extends ShaderProgram{

    List<Vector3f> vertices;
    int vao;
    int vbo;
    public Object2d(List<ShaderModuleData> shaderModuleDataList, List<Vector3f> vertices) {
        super(shaderModuleDataList);
        this.vertices = vertices;
        setupVAOVBO();
    }
    public void setupVAOVBO(){
        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, Utils.listoFloat(vertices), GL_STATIC_DRAW);

    }
    public void draw(){
        drawSetup();
        //draw vertice
        //Optional
        glLineWidth(1); //ketebalan garis
        glPointSize(0); //besar kecil vertex

        //wajib
        //GL TRIANGLE BISA DICUSTOM
        //kalau mau garis bisa GL_LINE dsb
        //INI YANG BIASA SERING DIPAKAI =
        //GL_LINE_STRIP
        //GL_LINE_LOOP
        //GL_TRIANGLES
        //GL_TRIANGLE_FAN
        //GL_POINT

        //First ini buat ngegambar dari index keberapa, kalau mau digambar semua dari 0 berarti.
        //vertices.size ini buat ngasi tau ngegambar dari titik first keberapa hingga keberapa, kalau mau semuanya lgsg vertices.size
        //kalau mau sampai ke dua ya isi langsung habis first lgsg koma 2
        glDrawArrays(GL_TRIANGLES,0,vertices.size());
    }
    public void drawSetup(){
        bind();

        //Bind VBO
        glEnableVertexAttribArray(0);
        glBindBuffer(GL_ARRAY_BUFFER, vbo); //yang di kirim
        //kirim ke 0
        //karena 1 vertex itu 3 (x,y,z) maka size 3, kalau 2 vertex (x,y) maka size 2
        //kita kasih tau yang kita kirim tipe datanya GL_FLOAT
        //pointer 0 karena ingin komputer baca VBO dari awal. Default nya emang 0.
        glVertexAttribPointer(0,3,GL_FLOAT,false,0,0);
    }
}