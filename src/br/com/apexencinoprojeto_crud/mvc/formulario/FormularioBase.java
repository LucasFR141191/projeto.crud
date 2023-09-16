package br.com.apexencinoprojeto_crud.mvc.formulario;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import br.com.apexencinoprojeto_crud.mvc.conexao.Conexao;

import javax.swing.JScrollPane;

public class FormularioBase extends JFrame {

	private JPanel contentPane;
	private JTextField txtCodigo;
	private JTextField txtNome;
	private JTable tabela;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormularioBase frame = new FormularioBase();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FormularioBase() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JLabel labelCódigo = new JLabel("Código:");

		JLabel labelNome = new JLabel("Nome:");

		txtCodigo = new JTextField();
		txtCodigo.setColumns(10);

		txtNome = new JTextField();
		txtNome.setColumns(10);

		JButton botaoCadastrar = new JButton("Cadastrar");
		botaoCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nome = txtNome.getText();

				try {
					Conexao conexao = new Conexao();
					String sql = "INSERT INTO usuarios (nomeUsuario) VALUES (?)";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
					pstmt.setString(1, nome);
					pstmt.execute();
					System.out.println("Cadastro realizado com sucesso!");
				} catch (Exception e2) {
					System.out.println("Falha ou cadastrar" + e);
					// TODO: handle exception

				}
				tabela.setModel(listarDados());
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
			}
		});
		JButton botaoCancelar = new JButton("Cancelar");
		botaoCancelar.setEnabled(false);
		
		JButton botaoExcluir = new JButton("Excluir");
		botaoExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigoUsuario = Integer.parseInt(txtCodigo.getText());
				try {
					Conexao conexao = new Conexao();
					String sql = "DELETE FROM usuarios WHERE idUsuario = ?";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
					pstmt.setInt(1, codigoUsuario);
					pstmt.execute(sql);
					
				} catch (Exception e2) {
					System.out.println("Falha ou excluir"+ e2.getMessage());
					
				}
				tabela.setModel(listarDados());
				txtCodigo.setText("");
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
				botaoExcluir.setEnabled(false);
				botaoCancelar.setEnabled(false);
				
			}
		});
		botaoExcluir.setEnabled(false);

		JButton botaoAlterar = new JButton("Alterar");
		botaoAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int codigoUsuario = Integer.parseInt(txtCodigo.getText());
				String nomeUsuario = txtNome.getText();
				try {
					Conexao conexao = new Conexao();
					String sql = "UPDATE usuarios SET nomeUsuario = ? WHERE idUsuario = ?";
					PreparedStatement pstmt = conexao.conectar().prepareStatement(sql);
					pstmt.setString(1,nomeUsuario);
					pstmt.setInt(2, codigoUsuario);
					
					pstmt.execute(sql);
					
				} catch (Exception e2) {
					System.out.println("Falha ao alterar"+ e2.getLocalizedMessage());
					
					// TODO: handle exception
				}
				tabela.setModel(listarDados());
				txtCodigo.setText("");
				txtNome.setText("");
				botaoCadastrar.setEnabled(true);
				botaoAlterar.setEnabled(false);
				botaoExcluir.setEnabled(false);
				botaoCancelar.setEnabled(false);
				
			}
		});
		botaoAlterar.setEnabled(false);
		
		

		

		JScrollPane scrollPane = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addComponent(labelCódigo)
										.addComponent(labelNome, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(txtCodigo, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)
										.addComponent(txtNome, GroupLayout.DEFAULT_SIZE, 357, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(botaoCadastrar, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(botaoAlterar, GroupLayout.PREFERRED_SIZE, 89,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10)
								.addComponent(botaoExcluir, GroupLayout.PREFERRED_SIZE, 89,
										GroupLayout.PREFERRED_SIZE)
								.addGap(10).addComponent(botaoCancelar, GroupLayout.PREFERRED_SIZE, 89,
										GroupLayout.PREFERRED_SIZE))
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 412, GroupLayout.PREFERRED_SIZE))
				.addContainerGap()));
		gl_contentPane.setVerticalGroup(gl_contentPane.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPane
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(labelCódigo).addComponent(
						txtCodigo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING).addComponent(labelNome).addComponent(
						txtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE).addComponent(botaoCadastrar)
						.addComponent(botaoExcluir).addComponent(botaoCancelar).addComponent(botaoAlterar))
				.addPreferredGap(ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
				.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 147, GroupLayout.PREFERRED_SIZE)));

		tabela = new JTable();
		scrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				botaoCadastrar.setEnabled(false);
				botaoAlterar.setEnabled(true);
				botaoExcluir.setEnabled(true);
				botaoCancelar.setEnabled(true);
				
				int linhaSelecionada = tabela.getSelectedRow();
				txtCodigo.setText(tabela.getValueAt(linhaSelecionada, 0). toString());
				txtNome.setText(tabela.getValueAt(linhaSelecionada, 1). toString());
			}
		});

		tabela.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "C\u00F3digo:", "Nome:" }));
		scrollPane.setViewportView(tabela);
		contentPane.setLayout(gl_contentPane);
	}

	private static DefaultTableModel listarDados() {
		DefaultTableModel dados = new DefaultTableModel();
		dados.addColumn("código");
		dados.addColumn("Nome");
		try {
			Conexao conexao = new Conexao();
			String sql = "SELECT * FROM usuarios ";
			Statement stmt = conexao.conectar().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				dados.addRow(new Object[] { rs.getInt(1), rs.getString(2) });
			}

		} catch (Exception e) {
			System.out.println("Falha ao selecionar os usuários" + e);
			// TODO: handle exception
		}
		return dados;

	}
}
