package com.frederikam.trianglesolver;

import java.util.ArrayList;
import java.util.List;

public class Triangle {

    public enum ComponentType {
        ANGLE_A(true),
        ANGLE_B(true),
        ANGLE_C(true),
        SIDE_A(false),
        SIDE_B(false),
        SIDE_C(false);

        boolean isAngle;

        ComponentType(boolean isAngle) {
            this.isAngle = isAngle;
        }

        public boolean isAngle() {
            return isAngle;
        }
    }

    public class Component {
        public final ComponentType type;
        public Double value;

        private Component(ComponentType type, Double value) {
            this.type = type;
            this.value = value;
        }
    }

    public Double A;
    public Double B;
    public Double C;
    public Double a;
    public Double b;
    public Double c;

    public Triangle(Double A, Double B, Double C, Double a, Double b, Double c) {
        this.A = A;
        this.B = B;
        this.C = C;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public Component get(ComponentType type) {
        switch (type) {
            case ANGLE_A:
                return new Component(ComponentType.ANGLE_A, A);
            case ANGLE_B:
                return new Component(ComponentType.ANGLE_B, B);
            case ANGLE_C:
                return new Component(ComponentType.ANGLE_C, C);
            case SIDE_A:
                return new Component(ComponentType.SIDE_A, a);
            case SIDE_B:
                return new Component(ComponentType.SIDE_B, b);
            case SIDE_C:
                return new Component(ComponentType.SIDE_C, c);
            default:
                throw new RuntimeException("No valid case");
        }
    }

    public void put(ComponentType type, Double value) {
        switch (type) {
            case ANGLE_A:
                A = value;
            case ANGLE_B:
                B = value;
                break;
            case ANGLE_C:
                C = value;
                break;
            case SIDE_A:
                a = value;
                break;
            case SIDE_B:
                b = value;
                break;
            case SIDE_C:
                c = value;
                break;
            default:
                throw new RuntimeException("No valid case");
        }
    }

    public List<Component> getAngles() {
        List<Component> components = new ArrayList<>();
        if(A != null)
            components.add(new Component(ComponentType.ANGLE_A, A));
        if(B != null)
            components.add(new Component(ComponentType.ANGLE_B, B));
        if(C != null)
            components.add(new Component(ComponentType.ANGLE_C, C));

        return components;
    }

    public List<Component> getSides() {
        List<Component> components = new ArrayList<>();
        if(a != null)
            components.add(new Component(ComponentType.SIDE_A, a));
        if(b != null)
            components.add(new Component(ComponentType.SIDE_B, b));
        if(c != null)
            components.add(new Component(ComponentType.SIDE_C, c));

        return components;
    }

    public int getAngleCount() {
        return getAngles().size();
    }

    public int getSideCount() {
        return getSides().size();
    }

    public Component getOpposing(ComponentType type) {
        switch (type) {
            case ANGLE_A:
                return get(ComponentType.SIDE_A);
            case ANGLE_B:
                return get(ComponentType.SIDE_B);
            case ANGLE_C:
                return get(ComponentType.SIDE_C);
            case SIDE_A:
                return get(ComponentType.ANGLE_A);
            case SIDE_B:
                return get(ComponentType.ANGLE_B);
            case SIDE_C:
                return get(ComponentType.ANGLE_C);
            default:
                throw new RuntimeException("No valid case");
        }
    }

    public Component getOpposing(Component comp) {
        return getOpposing(comp.type);
    }

    public ComponentType getNextRemainingAngle() {
        List<Component> components = getAngles();
        if(components.size() == 3) {
            throw new IllegalArgumentException("List too long");
        }

        boolean present[] = {
                false,
                false
        };

        for (Component comp : components) {
            switch (comp.type) {
                case ANGLE_A:
                    present[0] = true;
                    break;
                case ANGLE_B:
                    present[1] = true;
                    break;
            }
        }

        if(present[0]) {
            return ComponentType.ANGLE_A;
        } else if(present[1]) {
            return ComponentType.ANGLE_B;
        } else {
            return ComponentType.ANGLE_C;
        }
    }

    public ComponentType getNextRemainingSide() {
        List<Component> components = getSides();
        if(components.size() >= 2) {
            throw new IllegalArgumentException("List too long");
        }

        boolean present[] = {
                false,
                false
        };

        for (Component comp : components) {
            switch (comp.type) {
                case SIDE_A:
                    present[0] = true;
                    break;
                case SIDE_B:
                    present[1] = true;
                    break;
            }
        }

        if(present[0]) {
            return ComponentType.SIDE_A;
        } else if(present[1]) {
            return ComponentType.SIDE_B;
        } else {
            return ComponentType.SIDE_C;
        }
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "A=" + A +
                ", B=" + B +
                ", C=" + C +
                ", a=" + a +
                ", b=" + b +
                ", c=" + c +
                '}';
    }
}
