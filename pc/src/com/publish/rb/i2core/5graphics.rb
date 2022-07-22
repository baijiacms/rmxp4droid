module Graphics
  def self.frame_count()
    return YGraphics.frame_count
  end
  def self.frame_count=(fc)
    YGraphics.frame_count = fc
  end
  def self.update()
    YGraphics.update()
  end
  def self.freeze()
    YGraphics.freeze()
  end
  def self.width()
    return YGraphics.width()
  end
  def self.height()
    return YGraphics.height()
  end
  def self.frame_reset()
    YGraphics.frame_reset()
  end
  #def self.transition()
  #  YGraphics.transition(8, null, 40)
  #end 
  def self.transition(duration=8, filename=null, vague=40)
    YGraphics.transition(duration, filename, vague)
  end
  #def self.transition(duration, filename, vague)
  #  YGraphics.transition(duration, filename, vague)
  #end
  def self.wait(duration)
    YGraphics.wait(duration)
  end
  def self.frame_rate()
    return YGraphics.frame_rate()
  end
  def self.method_missing(m, *args, &block)   
     
  end
end