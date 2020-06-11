package game.core.components;

public class HitBox extends Component {

	private float x, y, z, w, h, d = 0;
	@Override
	public void start() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(float interval) {
		// TODO Auto-generated method stub

	}
	public void updateBox(float x, float y, float z, float w, float h, float d) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
		this.h = h;
		this.d = d;
	}
	public boolean contains(HitBox other) {
		if (x <= other.x + other.w && y <= other.y + other.h && z <= other.z + other.d) {
			if (x+ w > other.x && y + h > other.y && z + d > other.z) {
				return true;
			}
		}
		return false;

		
	}

	@Override
	public void onRemove() {
		// TODO Auto-generated method stub

	}

}
