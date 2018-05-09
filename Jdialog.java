package fechasSpinner;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;

import java.util.Date;
import java.util.Locale;
import java.util.Calendar;

import java.time.LocalDate;
import java.time.format.TextStyle;

/**
 * JDialog - Principal del paquete
 * 
 * @author Francisco Ramirez
 *
 */
public class Jdialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private final JPanel contentPanel = new JPanel();
	private Locale locale = Locale.getDefault();// para que tome el idioma
												// español del host

	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Jdialog dialog = new Jdialog();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Jdialog() {
		setTitle("Fechas - Francisco Ramirez");
		setBounds(100, 100, 632, 126);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblFechaNacimiento = new JLabel("Fecha nacimiento");
			contentPanel.add(lblFechaNacimiento);
		}
		JSpinner spinner = new JSpinner();

		{
			spinner.setModel(new SpinnerDateModel(new Date(1491688800000L), null, null, Calendar.DAY_OF_YEAR));
			spinner.setEditor(new JSpinner.DateEditor(spinner, Jdialog.DD_MM_YYYY));
			contentPanel.add(spinner);

		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnFechaNacimiento = new JButton("Fecha nacimiento");
				btnFechaNacimiento.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							LocalDate fechaDeNacimiento = getFechaNacimiento(spinner);
							JOptionPane.showMessageDialog(null,
									"Yo nací en " + Fecha.getDiaDeLaSemanaFechaPasada(fechaDeNacimiento)
											.getDisplayName(TextStyle.FULL, locale) + ".");
						} catch (FechaNoPasadaException e1) {
							JOptionPane.showMessageDialog(null, e1.getMessage());
						}

					}
				});
				{
					JButton btn25Cumpleannos = new JButton("25 cumpleaños");
					btn25Cumpleannos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							LocalDate fechaDeNacimiento = getFechaNacimiento(spinner);
							LocalDate veinticincoCumpleannos = fechaDeNacimiento.plusYears(25);
							JOptionPane.showMessageDialog(null, "Mi 25 cumpleaños "
									+ (veinticincoCumpleannos.isBefore(LocalDate.now()) ? "fue" : "será") + " un "
									+ veinticincoCumpleannos.getDayOfWeek().getDisplayName(TextStyle.FULL, locale)
									+ ".");
						}
					});
					buttonPane.add(btn25Cumpleannos);
				}
				buttonPane.add(btnFechaNacimiento);
			}
			{
				JButton btnHoyEs = new JButton("Hoy es");
				btnHoyEs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						LocalDate localDate = LocalDate.now();
						JOptionPane.showMessageDialog(null,
								"Hoy es " + localDate.getDayOfWeek().getDisplayName(TextStyle.FULL, locale) + ", "
										+ localDate.getDayOfMonth() + " de "
										+ localDate.getMonth().getDisplayName(TextStyle.FULL, locale) + " de "
										+ localDate.getYear() + ".");
					}
				});
				{
					JButton btnDiasParaCumpleannos = new JButton("Días para mi cumpleaños");
					btnDiasParaCumpleannos.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							LocalDate fechaDeNacimiento = getFechaNacimiento(spinner);
							JOptionPane.showMessageDialog(null, Fecha.cuentaAtrasCumpleannos(fechaDeNacimiento));
						}
					});
					btnDiasParaCumpleannos.setActionCommand("OK");
					buttonPane.add(btnDiasParaCumpleannos);
					getRootPane().setDefaultButton(btnDiasParaCumpleannos);
				}
				buttonPane.add(btnHoyEs);
			}
		}
	}

	/**
	 * Lee la fecha de nacimiento del spinner
	 * 
	 * @param spinner
	 * @return
	 */
	private LocalDate getFechaNacimiento(JSpinner spinner) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date) spinner.getModel().getValue());
		LocalDate fechaDeNacimiento = LocalDate.of(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
				calendar.get(Calendar.DAY_OF_MONTH));
		return fechaDeNacimiento;
	}
}


