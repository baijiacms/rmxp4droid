
class Bitmap
  #--------------
  # ● erase
  #--------------
  def erase(*args)
    if args.size == 1
      rect = args[0]
    elsif args.size == 4
      rect = Rect.new(*args)
    end
    fill_rect(rect, Color.new(0, 0, 0, 0))
  end
end