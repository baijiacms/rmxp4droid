# -- PLANE --
class Plane
  def disposed?
    isDisposed
  end
  def z=(z)
    setZ(z)
  end
  def bitmap=(bitmap)
    setBitmap(bitmap)
  end
  def viewport=(z)
    setViewport(z)
  end
end
# -- BITMAP --
class Bitmap
  def disposed?
    isDisposed
  end
end

# -- VIEWPORT --
class Viewport
  def disposed?
    isDisposed
  end
  def z=(z)
    setZ(z)
  end
end