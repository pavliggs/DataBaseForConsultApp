package ru.progwards.java2.lib;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Objects;

public enum DataBase {
    INSTANCE;

    public final static String DB_PATH = "C:\\Users\\Эльдорадо\\STUDY\\Java\\consult_db\\";

    public Users users = new Users();
    public Consultations consultations = new Consultations();
    public Schedule schedule = new Schedule();
    public Settings settings = new Settings();

    // таблица пользователи
    public static class Users extends AbstractDbTable<String, Users.User> {
        public static class User {
            private final String login;
            private final String password;
            private final boolean needChangePassword;
            private final String name;
            private final boolean isMentor;
            private final String email;
            private final String progwardsAccountLink;
            private final String discordName;
            private final String image;

            public User(String login, String password, boolean needChangePassword, String name, boolean isMentor,
                        String email, String progwardsAccountLink, String discordName, String image) {
                this.login = login;
                this.password = password;
                this.needChangePassword = needChangePassword;
                this.name = name;
                this.isMentor = isMentor;
                this.email = email;
                this.progwardsAccountLink = progwardsAccountLink;
                this.discordName = discordName;
                this.image = image;
            }

            public String getLogin() {
                return login;
            }

            public String getPassword() {
                return password;
            }

            public boolean isNeedChangePassword() {
                return needChangePassword;
            }

            public String getName() {
                return name;
            }

            public boolean isMentor() {
                return isMentor;
            }

            public String getEmail() {
                return email;
            }

            public String getProgwardsAccountLink() {
                return progwardsAccountLink;
            }

            public String getDiscordName() {
                return discordName;
            }

            public String getImage() {
                return image;
            }

            @Override
            public String toString() {
                return login;
            }
        }

        private Users() {
            super(new TypeToken<ArrayList<User>>(){}.getType());
        }

        @Override
        public String getTableName() {
            return "users.json";
        }

        @Override
        public String getKey(User elem) {
            return elem.login;
        }
    }

    // таблица консультации
    public static class Consultations extends AbstractDbTable<Consultations.Key, Consultations.Consultation> {
        public static class Consultation {
            private final String mentor;
            private final long start;
            private final long duration;
            private final String student;
            private final String comment;

            public Consultation(String mentor, long start, long duration, String student, String comment) {
                this.mentor = mentor;
                this.start = start;
                this.duration = duration;
                this.student = student;
                this.comment = comment;
            }

            public String getMentor() {
                return mentor;
            }

            public long getStart() {
                return start;
            }

            public long getDuration() {
                return duration;
            }

            public String getStudent() {
                return student;
            }

            public String getComment() {
                return comment;
            }
        }

        public static class Key {
            private final String mentor;
            private final long start;

            public Key(String mentor, long start) {
                this.mentor = mentor;
                this.start = start;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return start == key.start &&
                        Objects.equals(mentor, key.mentor);
            }

            @Override
            public int hashCode() {
                return Objects.hash(mentor, start);
            }
        }

        private Consultations() {
            super(new TypeToken<ArrayList<Consultation>>(){}.getType());
        }

        @Override
        public String getTableName() {
            return "consultations.json";
        }

        @Override
        public Key getKey(Consultation elem) {
            return new Key(elem.mentor, elem.start);
        }
    }

    // таблица расписание
    public static class Schedule extends AbstractDbTable<Schedule.Key, Schedule.Value> {
        public static class Value {
            private final String mentor;
            private final int dayOfWeek;
            private final long start;
            private final long duration;

            public Value(String mentor, int dayOfWeek, long start, long duration) {
                this.mentor = mentor;
                this.dayOfWeek = dayOfWeek;
                this.start = start;
                this.duration = duration;
            }

            public String getMentor() {
                return mentor;
            }

            public int getDayOfWeek() {
                return dayOfWeek;
            }

            public long getStart() {
                return start;
            }

            public long getDuration() {
                return duration;
            }
        }

        public static class Key {
            private final String mentor;
            private final int dayOfWeek;
            private final long start;

            public Key(String mentor, int dayOfWeek, long start) {
                this.mentor = mentor;
                this.dayOfWeek = dayOfWeek;
                this.start = start;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return dayOfWeek == key.dayOfWeek &&
                        start == key.start &&
                        Objects.equals(mentor, key.mentor);
            }

            @Override
            public int hashCode() {
                return Objects.hash(mentor, dayOfWeek, start);
            }
        }

        private Schedule() {
            super(new TypeToken<ArrayList<Value>>(){}.getType());
        }

        @Override
        public String getTableName() {
            return "schedule.json";
        }

        @Override
        public Key getKey(Value elem) {
            return new Key(elem.mentor, elem.dayOfWeek, elem.start);
        }
    }

    // таблица настройки
    public static class Settings extends AbstractDbTable<String, Settings.Record> {
        public static class Record {
            private final String name;
            private final String value;

            public Record(String name, String value) {
                this.name = name;
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }
        }

        private Settings() {
            super(new TypeToken<ArrayList<Record>>(){}.getType());
        }

        @Override
        public String getTableName() {
            return "settings.json";
        }

        @Override
        public String getKey(Record elem) {
            return elem.name;
        }
    }

    public static void main(String[] args) throws Exception {
//        INSTANCE.users.readAll();

        DataBase.INSTANCE.consultations.put(new Consultations.Consultation("mazneff", 1139090999,
                12939, "name2", ""));
        DataBase.INSTANCE.consultations.put(new Consultations.Consultation("mazneff", 1139090999,
                12939, "name2", ""));

        DataBase.INSTANCE.schedule.put(new Schedule.Value("mazneff", 1, 1242424232, 8989));

        DataBase.INSTANCE.settings.put(new Settings.Record("123", "erere"));

        System.out.println(DataBase.INSTANCE.users.remove("login3"));

        System.out.println(DataBase.INSTANCE.users.remove("login3"));
    }
}
