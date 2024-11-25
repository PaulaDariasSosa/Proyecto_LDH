package es.ull.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @brief Clase que representa a un usuario
 */
public class User {
    private String name; /// Nombre del usuario

    /**
     * @brief Constructor por defecto
     */
    public User() {
        this.name = "";
    }

    /**
     * @brief Constructor con parámetros
     * @param name
     */
    public User(String name) {
        this.name = name;
    }

    /**
     * @brief Método para obtener el nombre
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @brief Método para establecer el nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @brief Método para pedir el nombre mediante una interfaz gráfica
     */
    public void askForName() {
        JDialog dialog = new JDialog((Frame) null, "Introduce tu Nombre", true); // Modal dialog
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.setSize(300, 150);
        dialog.setLayout(new BorderLayout());

        JLabel label = new JLabel("Introduce tu nombre:", SwingConstants.CENTER);
        JTextField nameField = new JTextField();
        JButton submitButton = new JButton("Guardar");

        // Panel para el campo de texto y el botón
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(nameField, BorderLayout.CENTER);
        inputPanel.add(submitButton, BorderLayout.SOUTH);

        dialog.add(label, BorderLayout.NORTH);
        dialog.add(inputPanel, BorderLayout.CENTER);

        // Acción al hacer clic en el botón
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredName = nameField.getText().trim();
                if (!enteredName.isEmpty()) {
                    name = enteredName;
                    dialog.dispose(); // Cierra el diálogo
                } else {
                    JOptionPane.showMessageDialog(dialog, "Por favor, introduce un nombre válido.");
                }
            }
        });

        dialog.setLocationRelativeTo(null); // Centra el diálogo en la pantalla
        dialog.setVisible(true); // Bloquea la ejecución hasta que se cierre el diálogo
    }
}
