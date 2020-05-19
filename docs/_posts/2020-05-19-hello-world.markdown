---
layout: post
title:  "Hello world!"
date:   2020-05-19 13:18:52 +0200
categories: general
# `` highlight
#   {% highlight <lang>%}
#   {% endhighlight %}
---
If you found your way here it looks like you've stumbled upon the project page for my game engine, Joe3D!

So, what's the goal? What's the deal? Why another game engine, you might ask? Well, graphics are kinda cool and I wanna know how to do them, that's why!

But it's not only that. I've dabbled very shallowly in various game engines but I never really felt satisfied not knowing what was going on under the hood. I also realized I'm more interested in the architecture and design of game engines, rather than the games themselves. So here we are. :)

The plan is to share my design ideas, my code and just general thoughts about the process on this dev blog. At the moment there's not too much to talk about. Rest assured, as soon as I've finished up some more skeleton work, we'll dive right into it!

So, for now, I'll just you with this screenshot and code snippet of some Suzannes enjoying their time in Joe3D!

Take care, y'all!

![Ook!](assets/imgs/suzannes.png)

{% highlight java %}
public static void main(String[] args) {
  Camera camera = new Camera(Consts.WINDOW_WIDTH, Consts.WINDOW_HEIGHT);
  Scene scene = new Scene(camera);

  GameObject suz1 = new RenderObject(MeshBuilder.build(Paths.MODELS + "monkey.obj"), 
    new Material(Color.orange));
  GameObject suz2 = new RenderObject(MeshBuilder.build(Paths.MODELS + "monkey.obj"), 
    new Material(Color.pink));
  GameObject suz3 = new RenderObject(MeshBuilder.build(Paths.MODELS + "monkey.obj"), 
    new Material(Color.cyan));

  suz1.getTransform().translate(2, 0, 0);
  suz1.getTransform().rotate(-30, 0, 1, 0);
  suz2.getTransform().translate(-2, 0, 0);
  suz2.getTransform().rotate(45, 0, 1, 0);
  suz3.getTransform().translate(0, 0, 1);
  suz3.getTransform().rotate(45, 0, 0, 1);
  camera.getTransform().translate(0,0, 5);

  scene.add(suz1);
  scene.add(suz2);
  scene.add(suz3);

  new Renderer(scene).run();
}
{% endhighlight %}