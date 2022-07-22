class Sprite
  attr_accessor :mysprites
   attr_accessor :x,:y,:src_rect,:bitmap
  attr_accessor :z,:ox, :oy, :zoom_x, :zoom_y
    attr_reader :opacity, :viewport
  attr_accessor :src_rect, :bitmap, :visible
  
  
  attr_accessor :angle, :mirror, :color, :tone, :blend_type
  attr_accessor :bush_depth
  
  def initialize(viewport = nil)
 	@mysprites=Sprites.new(viewport)
 	@x=@mysprites.getX()
 	@y=@mysprites.getY()
 	@z=@mysprites.getZ()
 	@ox=@mysprites.getOx()
 	@oy=@mysprites.getOy()
 	@zoom_x=@mysprites.getZoom_x()
 	@zoom_y=@mysprites.getZoom_y()
 	@src_rect=@mysprites.getSrc_rect()
 	@bitmap=@mysprites.bitmap()
 	@opacity=@mysprites.opacity()
 	@viewport=@mysprites.viewport()
 	@visible=@mysprites.isVisible()
 	@angle=@mysprites.getAngle()
 	@mirror=@mysprites.isMirror()
 	@color=@mysprites.getColor()
 	@tone=@mysprites.tone()
 	@blend_type=@mysprites.getBlend_type()
 	@bush_depth=@mysprites.getBush_depth()
  end
  
  def update
  end
    def bush_depth=(int)
    @bush_depth=int
    @mysprites.setBush_depth(int)
  end
  
   def blend_type=(int)
    @blend_type=int
    @mysprites.setBlend_type(int)
  end
  
     def tone=(int)
    @tone=int
    @mysprites.setTone(int)
  end
  
   def color=(int)
    @color=int
    @mysprites.setColor(int)
  end
  
   def mirror=(int)
    @mirror=int
    @mysprites.setMirror(int)
  end
   def angle=(int)
    @angle=int
    @mysprites.setAngle(int)
  end
    def visible=(visibles)
    @visible=visibles
    @mysprites.setVisible(visibles)
  end
   def visible?
    @mysprites.isVisible()
  end
 
    
  def width
     @mysprites.width()
  end
  
  def height
     @mysprites.height()
  end
  
  
  def dispose
     @mysprites.dispose()
  end
  
  def disposed?
     @mysprites.isDisposed()
  end

  def x=(int)
  @x=int
   @mysprites.setX(int)
  end
   
    def y=(int)
    @y=int
     @mysprites.setY(int)
  end
      def src_rect=(src_rect)
    @src_rect=src_rect
     @mysprites.setSrc_rect(src_rect)
  end
      def bitmap=(bitmaps)
      @bitmap=bitmaps
    @mysprites.setBitmap(bitmaps)
    @src_rect=@mysprites.getSrc_rect()
  end
   
   
    def ox=(int)
    @ox=int
     @mysprites.setOx(int)
  end
      def oy=(int)
      @oy=int
     @mysprites.setOy(int)
  end
      def zoom_x=(int)
      @zoom_x=int
     @mysprites.setZoom_x(int)
  end
     def zoom_y=(int)
     @zoom_y=int
     @mysprites.setZoom_y(int)
  end
  def opacity=(int)
  @opacity=int
     @mysprites.setOpacity(int)
  end

 
  def tone=(tone)
    @mysprites.setTone(tone)
  end
   def tone?
    @mysprites.tone()
  end
     def z=(int)
    @z=int
     @mysprites.setZ(int)
  end
  def setZ(int)
  @z=int
    @mysprites.setZ(int)
  end
  
   
  
  def viewport=(z)
 	@viewport=z
    @mysprites.setViewport(z)
  end
end