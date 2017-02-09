[![License](https://img.shields.io/badge/license-Apache%202-green.svg)](https://www.apache.org/licenses/LICENSE-2.0)

`compile 'com.xsf:zeusLog:1.0.0'`

# 粒子效果动画

效果图：
参看[效果图](https://xsfelvis.github.io/2016/11/28/%E7%B2%92%E5%AD%90%E5%8A%A8%E7%94%BB%E5%AD%A6%E4%B9%A0/)

# 简介
提供了6中粒子动画效果，框架支持扩展，若有新的粒子需求可以继承Particle实现自己的需求，整体框架如下

![particleUml](http://ogopjinry.bkt.clouddn.com/Particle.jpeg)

# 使用

`compile 'com.xsf:Explosion:1.0.0'`

或者使用jar包[jar包下载地址](http://ogopjinry.bkt.clouddn.com/explosion.jar)，然后在Actvity中按照如下方式使用即可。

``` java
private ExplosionView explosionView;
//找到对应的控件
tvNormal = (TextView) findViewById(R.id.tvNormal);
ivVx = (ImageView) findViewById(R.id.ivVx);
//设置监听即可
explosionView = new ExplosionView(this, new ParticleFactory(ParticleFactory.NORMAL));     explosionView.addExplosionListener(tvNormal);
explosionView.addExplosionListener(ivVx);
```

# 致谢
站在巨人的肩膀上会领略到更多的风景。在他们的基础上做了大量的重构。
Thanks 
- [https://github.com/Xieyupeng520/AZExplosion](https://github.com/Xieyupeng520/AZExplosion)
- [https://github.com/tyrantgit/ExplosionField](https://github.com/tyrantgit/ExplosionField)



