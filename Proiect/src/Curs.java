import java.util.*;

public class Curs{
    private String nume;
    private String descriere;
    private Profesor prof;
    private Set<Student> studenti = new HashSet<Student>();
    private HashMap<Student, Integer> note = new HashMap<Student, Integer>();

    //Consctructor Curs
    public Curs(String nume, String descriere, Profesor profu, Set<Student> studenti) {
        this.nume = nume;
        this.descriere = descriere;
        this.prof = profu;
        this.studenti = studenti;
    }

    public Curs(String[] splituri, Profesor prof, Set<Student> studenti)
    {
        this.nume = splituri[0];
        this.descriere = splituri[1];
        this.prof = prof;
        this.studenti = studenti;
    }

    public float mediaStudenti()
    {
        float media = 0;
        for(Student s : studenti)
        {
            media += note.get(s) != null ? note.get(s) : 0;
        }
        return  media/studenti.size();
    }
    //Afisarea studentilor
    public void ShowStudents() {
        System.out.println("Studentii de la acest curs sunt:");
        for (Student s : studenti) {
            System.out.println(s);
        }
    }

    public void ShowStudentNotes()
    {
        for (Student s : this.studenti) {
            System.out.println("Studentul: " + s.getNume() + " " + s.getPrenume() + " are nota: " + this.note.get(s));
        }
    }

    //Functie adaugare student
    public void AddStudent() {
        Student student = new Student("","", 0);
        student.CitireDateStudent();
        this.studenti.add(student);
    }

    //Functie de stergere a unui student
    public void RemoveStudent(Student s) {
        if(!this.studenti.remove(s))
            System.out.println("Studentul nu participa la acest curs!");
    }

    //Functie de modificare student
    public void UpdateStudent(Student svechi, Student snou) {
        if(!this.studenti.remove(svechi))
        {
            System.out.println("Numele studentului nu apare la acest curs!");
        }
        else
        {
            this.studenti.add(snou);
            System.out.println("Studentul a fost modificat cu succes!");
        }
    }

    //Functie de sortare studenti in functie de grupa
    public void SorteazaStudentiDupaGrupa()
    {
        this.studenti.stream().sorted((o1, o2) -> o1.comperToGrupa(o2)).forEach(System.out::println);
    }

    //Functie de sortare studenti in functie de nume
    public void SorteazaStudentiDupaNume()
    {
        this.studenti.stream().sorted((o1, o2) -> o1.comperTo(o2)).forEach(System.out::println);
    }

    @Override
    public String toString() {
        String str = "Curs: " + "nume=" + this.nume + ", descriere=" + this.descriere + ",\nprof=" + this.prof + ",\nstudenti:\n";
        int index = 0;
        for (Student s : this.studenti) {
            str += s + "\n";
            str += "Nota: " + this.note.get(s) + "\n";
        }
        return str;
    }

    //Functire de comparerea numelor a doua cursuri
    public int compareTo(Curs c)
    {
        if(this.nume.compareTo(c.nume) < 0)
            return -1;
        else
            if(this.nume.compareTo(c.nume) > 0)
                return 1;
        return 0;
    }

    //Functire de comparerea a numelor profesorilor a doua cursuri
    public int compareToProf(Curs c)
    {
        if(this.prof.getNume().compareTo(c.prof.getNume()) < 0)
            return -1;
        else
            if(this.prof.getNume().compareTo(c.prof.getNume()) > 0)
                return 1;
        return 0;
    }

    //Functire de comparerea a numarului de studenti de la 2 cursuri
    public int compareToNrStudenti(Curs c)
    {
        if(this.studenti.size() < c.studenti.size())
            return -1;
        else
            if(this.studenti.size() > c.studenti.size())
                return 1;
        return 0;
    }

    //Functie de modificare profesor
    public void UpdateProfesor(Profesor prof) {
        this.prof = prof;
    }

    //Functie de notare student
    public void noteazaStudentul(Student s, Integer nota) {
        this.note.put(s,nota);
    }

    public String getNume() {
        return this.nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getDescriere() {
        return this.descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public Profesor getProf() {
        return this.prof;
    }

    public void setProf(Profesor prof) {
        this.prof = prof;
    }

    public Set<Student> getStudenti() {
        return studenti;
    }

    public void setStudenti(Set<Student> studenti) {
        this.studenti = studenti;
    }

    public HashMap<Student, Integer> getNote() {
        return note;
    }

    public void setNote(HashMap<Student, Integer> note) {
        this.note = note;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Curs curs = (Curs) o;
        return Objects.equals(nume, curs.nume);
    }

    public void noteazaStudent(Student s, int notaStudentului) throws Exception {
        if ( this.studenti.contains(s)) {
            this.note.put(s, notaStudentului);
        } else {
            throw new Exception("Studentul " + s + " nu poate fi notat pentru ca nu participa la curs");
        }
    }

}