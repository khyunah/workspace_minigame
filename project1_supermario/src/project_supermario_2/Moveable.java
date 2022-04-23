package project_supermario_2;

public interface Moveable {
	void left();
	void right();
	default void up() {};
	void down();
}
